SUMMARY = "Linux-based operating system development and deployment tool"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=97285cb818cf231e6a36f72c82592235"

PV= "2012.7"
PR= "r0"

SRC_URI = "git://git.gnome.org/ostree;tag=v${PV}"
#SRC_URI = "git:///src/ostree;tag=40ebccb55ea80437d795eec57cca03ce45cba0cd"
S = "${WORKDIR}/git"

DEPENDS += "attr libarchive glib-2.0"

inherit autotools

EXTRA_OECONF = "--without-soup-gnome --with-libarchive"

FILES_${PN} += "${libdir}/ostree/"

BBCLASSEXTEND = "native"

