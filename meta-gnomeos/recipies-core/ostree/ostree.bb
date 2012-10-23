SUMMARY = "Linux-based operating system development and deployment tool"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=97285cb818cf231e6a36f72c82592235"

PV= "2012.11.23.g0d04738"
PR= "r0"

SRC_URI = "http://people.gnome.org/~walters/ostree-${PV}.tar.gz"
SRC_URI[sha256sum] = "530fc9ad7c5dd4d7ed1cababa578288d112d803144d8176a25df45906f568e95"
#SRC_URI = "git:///src/ostree;tag=40ebccb55ea80437d795eec57cca03ce45cba0cd"
#S = "${WORKDIR}/git"

DEPENDS += "attr libarchive glib-2.0"
DEPENDS_virtclass-native += "attr-native libarchive-native glib-2.0-native"

inherit autotools

EXTRA_OECONF = "--without-soup-gnome --with-libarchive"

FILES_${PN} += "${libdir}/ostree/"

BBCLASSEXTEND = "native"

