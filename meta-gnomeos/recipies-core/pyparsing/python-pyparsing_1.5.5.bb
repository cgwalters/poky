SUMMARY = "Python module for parsing"
SECTION = "devel/python"
HOMEPAGE = "http://pyparsing.wikispaces.com/Download+and+Installation"
DEPENDS = "python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb46329938e6bc829b256e37d5c1e31a"

PR = "r1"

SRC_URI = "http://cheeseshop.python.org/packages/source/p/pyparsing/pyparsing-1.5.5.tar.gz"

SRC_URI[md5sum] = "0beba391cba3082d7914c289bffa52ce"
SRC_URI[sha256sum] = "d1754df0801871d05a96f1cef50a4f32e5d40d49ea24aa1c6ca9529cbd562005"

S = "${WORKDIR}/pyparsing-${PV}"

inherit distutils
