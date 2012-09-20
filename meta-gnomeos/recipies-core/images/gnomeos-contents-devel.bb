#
# Copyright (C) 2011 Colin Walters <walters@verbum.org>
#
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit gnomeos-contents

RECIPE_PACKAGES += "autoconf \
		automake \
		binutils \
		binutils-symlinks \
		coreutils \
		cpp \
		cpp-symlinks \
		ccache \
		diffutils \
		gcc \
		gcc-symlinks \
		g++ \
		g++-symlinks \
		gettext \
		make \
		intltool \
		libtool \
		perl-module-re \
		perl-module-text-wrap \
		pkgconfig \
		findutils \
		less \
		ldd \
		file \
		python-dev \
		bison flex \
		git \
		gdb \
		zip \
		libxml-parser-perl \
		gettext-dev \
		libpci-dev \
		bzip2-dev \
		xz-dev \
		"

DEPENDS += "db"

IMAGE_INSTALL += "libc6-dev \
	      	  libgcc-s-dev \
	      	  linux-libc-headers-dev \
	      	  libz-dev \
	      	  libgdbm-dev \
	      	  libtool-dev \
	          libuuid-dev \
		  libblkid-dev \
		  libpam-dev \
		  libtiff-dev \
		  libjpeg-dev \
		  libltdl-dev \
		  libsndfile-dev \
		  libatomics-ops-dev \
		  libogg-dev \
		  speex-dev \
		  libvorbis-dev \
		  libstdc++-dev \
		  libcap-dev \
		  libcap-bin \
		  libgpg-error-dev \
		  libtasn1-dev \
		  libtasn1-bin \
		  libgcrypt-dev \
		  libattr-dev \
		  libacl-dev \
		  libgnutls-dev \
		  icu-dev \
		  curl-dev \
		  libcurl-dev \
		  ncurses-dev \
		  db-dev \
		  cracklib-dev \
		  e2fsprogs-dev \
		  krb5-dev \
		  "
