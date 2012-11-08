#
# Copyright (C) 2011 Colin Walters <walters@verbum.org>
#
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

PR = "1"

RDEPENDS += "\
	 util-linux-libuuid \
	 util-linux-blkid \
	 e2fsprogs-e2fsck \
	 e2fsprogs-mke2fs \
	 e2fsprogs-tune2fs \
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
	 eglibc-localedata-en-us \
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
	 netbase \
	 busybox \
	 update-alternatives-cworth \
	 coreutils \
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
	 libsndfile1 \
	 icu \
	 procps \
	 libpam \
	 attr \
	 acl \
	 bzip2 \
	 xz \
	 ncurses \
	 libvorbis \
	 speex \
	 python-modules \
	 python-misc \
	 openssh \
	 krb5 \
	 dejavu-fonts-ttf \
	 module-init-tools \
	 nss-altfiles \
	 "
