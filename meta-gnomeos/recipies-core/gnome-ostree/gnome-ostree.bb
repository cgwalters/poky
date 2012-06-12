SUMMARY = "GnomeOSTree manifest and build tool"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

SRC_URI = "git://git.gnome.org/gnome-ostree;tag=v2012.1"
S = "${WORKDIR}/git"

DEPENDS += "python"

inherit autotools

FILES_${PN} += "${bindir} ${libdir}/ostbuild"

BBCLASSEXTEND = "native"

