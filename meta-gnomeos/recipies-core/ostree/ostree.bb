SUMMARY = "GNOME OS management tool"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=97285cb818cf231e6a36f72c82592235"

SRC_URI = "git://git.gnome.org/ostree;tag=v2012.1"
S = "${WORKDIR}/git"

DEPENDS += "libarchive glib-2.0"

inherit autotools

EXTRA_OECONF = "--without-soup-gnome --with-libarchive"

FILES_${PN} += "${libdir}/ostree/ ${libdir}/ostbuild"

BBCLASSEXTEND = "native"

