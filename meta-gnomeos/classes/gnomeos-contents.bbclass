#
# Copyright (C) 2011 Colin Walters <walters@verbum.org>
#
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit rootfs_${IMAGE_PKGTYPE}

DEPENDS += "ostree-native"

IMAGE_INSTALL = "libuuid1 \
	         libblkid1 \
		 e2fsprogs-fsck \
		 e2fsprogs-e2fsck \
		 e2fsprogs-mke2fs \
		 e2fsprogs-tune2fs \
		 libpci3 \
		 libtiff3 \
		 libjpeg8 \
		 libltdl7 \
                 libstdc++6 \
                 libgnutls26 \
                 libogg0 \
		 eglibc-gconvs \
		 eglibc-binaries \
		 pam-plugin-cracklib \
		 pam-plugin-env \
		 pam-plugin-keyinit \
		 pam-plugin-limits \
		 pam-plugin-localuser \
		 pam-plugin-loginuid \
		 pam-plugin-unix \
		 pam-plugin-rootok \
		 pam-plugin-succeed-if \
		 pam-plugin-permit \
		 pam-plugin-nologin \
		 ncurses-terminfo-base \
		 cpio \
		 util-linux-mount \
		 dejavu-fonts-ttf \
		 "

RDEPENDS += " eglibc-locale \
	     tiff \
	     libogg \
	     libvorbis \
	     speex \
	     cpio \
	     libatomics-ops \
	     cracklib \
	     pciutils \
	    "

RECIPE_PACKAGES = "base-files \
		   base-passwd \
		   netbase \
		   busybox \
		   update-alternatives-cworth \
		   sysvinit \
		   udev \
		   tinylogin \
		   initscripts \
		   coreutils \
		   gnome-ostree \
		   strace \
		   bash \
		   tar \
		   grep \
		   gawk \
		   gzip \
		   less \
		   curl \
		   tzdata \
		   libsndfile1 \
		   icu \
		   procps \
		   libpam \
		   attr \
		   acl \
		   ncurses \
		   libvorbis \
		   speex \
		   python-modules \
		   python-misc \
		   openssh \
		   krb5 \
		   dejavu-fonts-ttf \
		   module-init-tools \
		   e2fsprogs-blkid \
		   nss-altfiles \
		   "

PACKAGE_INSTALL = "${RECIPE_PACKAGES} ${IMAGE_INSTALL}"

RDEPENDS += "${RECIPE_PACKAGES}"
DEPENDS += "makedevs-native virtual/fakeroot-native"

EXCLUDE_FROM_WORLD = "1"

do_rootfs[nostamp] = "1"
do_rootfs[dirs] = "${TOPDIR}"
do_rootfs[lockfiles] += "${IMAGE_ROOTFS}.lock"
do_build[nostamp] = "1"
do_rootfs[umask] = 022

def gnomeos_get_devtable_list(d):
    return bb.which(d.getVar('BBPATH', 1), 'files/device_table-minimal.txt')

# Must call real_do_rootfs() from inside here, rather than as a separate
# task, so that we have a single fakeroot context for the whole process.
fakeroot do_rootfs () {
        set -x
	rm -rf ${IMAGE_ROOTFS}
	rm -rf ${MULTILIB_TEMP_ROOTFS}
	mkdir -p ${IMAGE_ROOTFS}
	mkdir -p ${DEPLOY_DIR_IMAGE}

	rootfs_${IMAGE_PKGTYPE}_do_rootfs

	# Delete all of the init scripts; we have our own
	rm -f ${IMAGE_ROOTFS}/etc/init.d/*
	rm -f ${IMAGE_ROOTFS}/etc/rc*.d/*

	# Clear out the default fstab; everything we need right now is mounted
	# in the initramfs.
	cat < /dev/null > ${IMAGE_ROOTFS}/etc/fstab

	# Kill the Debian netbase stuff - we use NetworkManager
	rm -rf ${IMAGE_ROOTFS}/etc/network

	# We deploy kernels via an external mechanism; the modules
	# directory is just a bind mount to /sysroot.
	rm -rf ${IMAGE_ROOTFS}/lib/modules
	mkdir -p ${IMAGE_ROOTFS}/lib/modules

	# Blow away udev from poky in favor of our own
	rm ${IMAGE_ROOTFS}/sbin/udevd
	ln -s /usr/libexec/udevd ${IMAGE_ROOTFS}/sbin/udevd
	rm ${IMAGE_ROOTFS}/sbin/udevadm
	ln -s /usr/sbin/udevadm ${IMAGE_ROOTFS}/sbin/udevadm

	# Random configuration changes here
	sed -i -e 's,^DESTINATION=.*,DESTINATION=\"file\",' ${IMAGE_ROOTFS}/etc/syslog.conf

	# Empty out the default passwd file
	rm -f ${IMAGE_ROOTFS}/etc/passwd ${IMAGE_ROOTFS}/etc/group \
	  ${IMAGE_ROOTFS}/etc/shadow ${IMAGE_ROOTFS}/etc/gshadow
	touch ${IMAGE_ROOTFS}/etc/passwd ${IMAGE_ROOTFS}/etc/group
	# Delete backup files
	rm -f ${IMAGE_ROOTFS}/etc/passwd- ${IMAGE_ROOTFS}/etc/group- \
	  ${IMAGE_ROOTFS}/etc/shadow- ${IMAGE_ROOTFS}/etc/gshadow-
	cat > ${IMAGE_ROOTFS}/lib/passwd << EOF
root::0:0:root:/:/bin/sh
dbus:*:1:1:dbus:/:/bin/false
gdm:*:2:2:gdm:/var/lib/gdm:/bin/false
polkitd:*:3:3:polkitd:/:/bin/false
EOF

	cat > ${IMAGE_ROOTFS}/lib/group << EOF
root:*:0:root
dbus:*:1:
gdm:*:2:
polkitd:*:3:
EOF

	# Add "altfiles" NSS module to /etc/nsswitch.conf
	sed -i -e '/^passwd:/cpasswd: files altfiles' \
	       -e '/^group:/cgroup: files altfiles' \
               ${IMAGE_ROOTFS}/etc/nsswitch.conf

	# Adjustments for /etc -> {/var,/run} here
	ln -sf /run/resolv.conf ${IMAGE_ROOTFS}/etc/resolv.conf

	# Fix un-world-readable config file; no idea why this isn't. 
	chmod a+r ${IMAGE_ROOTFS}/etc/securetty

	TOPROOT_BIND_MOUNTS="home root tmp"
	OSTREE_BIND_MOUNTS="var"
	OSDIRS="dev proc mnt media run sys sysroot"
	READONLY_BIND_MOUNTS="bin etc lib sbin usr"
	
	rm -rf ${WORKDIR}/gnomeos-contents
	mkdir ${WORKDIR}/gnomeos-contents
        cd ${WORKDIR}/gnomeos-contents
	for d in $TOPROOT_BIND_MOUNTS $OSTREE_BIND_MOUNTS $OSDIRS; do
	    mkdir $d
	done
	chmod a=rwxt tmp

	for d in $READONLY_BIND_MOUNTS; do
            mv ${IMAGE_ROOTFS}/$d .
	done
	rm -rf ${IMAGE_ROOTFS}
	mv ${WORKDIR}/gnomeos-contents ${IMAGE_ROOTFS}

	# Keep a copy of what ended up in /var
	VARDATA_DEST=${IMAGE_NAME}.vardata.tar.gz
	(cd ${IMAGE_ROOTFS}/var && tar -zcv -f ${WORKDIR}/${VARDATA_DEST} .)	
	echo "Created ${VARDATA_DEST}"
	mv ${WORKDIR}/${VARDATA_DEST} ${DEPLOY_DIR_IMAGE}/

	DEST=${IMAGE_NAME}.rootfs.tar.gz
	(cd ${IMAGE_ROOTFS} && tar -zcv -f ${WORKDIR}/$DEST .)
	echo "Created $DEST"
	mv ${WORKDIR}/$DEST ${DEPLOY_DIR_IMAGE}/
	cd ${DEPLOY_DIR_IMAGE}/
	rm -f ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.tar.gz
	ln -s ${IMAGE_NAME}.rootfs.tar.gz ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.tar.gz
	echo "Created ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.tar.gz"

	set -x

	if echo ${IMAGE_LINK_NAME} | grep -q -e -runtime; then
	   ostree_target=runtime
	else
	   ostree_target=devel
	fi
	if test x${MACHINE_ARCH} = xqemux86; then
	   ostree_machine=i686
	else
	  if test x${MACHINE_ARCH} = xqemux86_64; then
	    ostree_machine=x86_64
	  else
	    echo "error: unknown machine from ${MACHINE_ARCH}"; exit 1
	  fi
	fi
	buildroot=gnomeos-3.6-${ostree_machine}-${ostree_target}
	base=bases/yocto/${buildroot}
	repo=${DEPLOY_DIR_IMAGE}/repo
	mkdir -p ${repo}
	if ! test -d ${repo}/objects; then
	   ostree --repo=${repo} init --archive
        fi
	ostree --repo=${repo} commit -s "${IMAGE_LINK_NAME}" --skip-if-unchanged "Build" -b ${base} --tree=tar=${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.tar.gz
	ostree --repo=${repo} diff "${base}" || true
}

log_check() {
	true
}

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
do_populate_sysroot[noexec] = "1"
do_package[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_rpm[noexec] = "1"

addtask rootfs before do_build
