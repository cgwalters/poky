#
# Copyright (C) 2011 Colin Walters <walters@verbum.org>
#
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

PR = "1"

DEPENDS += " linux-gnome-ostree"

RDEPENDS += "\
	 util-linux-libuuid \
	 util-linux-blkid \
	 e2fsprogs-e2fsck \
	 e2fsprogs-mke2fs \
	 e2fsprogs-tune2fs \
	 btrfs-tools \
	 gdbm \
	 pciutils \
	 tiff \
	 jpeg \
	 libexif \
	 libstdc++ \
	 gnutls \
	 libogg \
	 libssp \
	 eglibc-gconvs \
	 eglibc-binaries \
	 eglibc-localedatas \
	 eglibc-charmaps \
	 eglibc-locale \
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
	 util-linux-agetty \
	 dejavu-fonts-ttf \
	 kbd \
	 kbd-consolefonts \
	 kbd-keymaps \
	 kbd-unimaps \
	 kbd \
	 gdbm \
	 tiff \
	 libogg \
	 libvorbis \
	 speex \
	 cpio \
	 libatomics-ops \
	 alsa-lib \
	 cracklib \
	 pciutils \
	 base-files \
	 base-passwd \
	 busybox \
	 update-alternatives-cworth \
	 coreutils \
	 sed \
	 findutils \
	 strace \
	 bash \
	 tar \
	 grep \
	 gawk \
	 gzip \
	 less \
	 util-linux \
	 curl \
	 tzdata \
	 tzdata-africa \
	 tzdata-americas \
	 tzdata-antarctica \
	 tzdata-arctic \
	 tzdata-asia \
	 tzdata-atlantic \
	 tzdata-australia \
	 tzdata-europe \
	 tzdata-misc \
	 tzdata-pacific \
	 tzdata-posix \
	 tzdata-right \
	 libsndfile1 \
	 icu \
	 procps \
	 libpam \
	 attr \
	 acl \
	 libselinux \
	 policycoreutils \
	 bzip2 \
	 xz \
	 ncurses \
	 libvorbis \
	 speex \
	 nspr \
	 python-modules \
	 python-misc \
	 openssh \
	 krb5 \
	 dejavu-fonts-ttf \
	 module-init-tools \
	 nss-altfiles \
         llvm \
         sqlite3 libsqlite3 \
         expat \
         syslinux-extlinux \
         syslinux-mbr \
         syslinux-misc \
	 cyrus-sasl \
	 "
