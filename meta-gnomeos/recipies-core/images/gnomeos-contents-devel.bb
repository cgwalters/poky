#
# Copyright (C) 2011 Colin Walters <walters@verbum.org>
#
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit gnomeos-contents

PACKAGE_INSTALL += "\
		task-gnomeos-contents-devel \
		libltdl-dev \
		libgcc-s-dev \
		libc6-dbg \
		"

DEPENDS += " task-gnomeos-contents-devel "
