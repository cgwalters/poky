DESCRIPTION = "\
NSS module to read user/group information from files in \
alternate locations."
HOMEPAGE = "https://github.com/aperezdc/nss-altfiles"
AUTHOR = "Adrian Perez <aperez@igalia.com>"
SECTION = "libs"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb1949d8d807e528c1673da700aff41f"
SRC_URI = "http://github.com/downloads/aperezdc/${PN}/${PN}-${PV}.tar.xz"
SRC_URI[sha256sum] = "53d26e6a4cdb1b662098db5b6dfa71701eeb957299895d021098f135ef3e4ad6"

PV = "2.13.2"
PR = "r1"
DEPENDS += "eglibc"

# Generate only an extra split-debug package (no -doc/-dev packages)
#
PACKAGES = "${PN} ${PN}-dbg"

# The package_rpm would rename generated packages to libnss_altfiles,
# so override the package names to get nss-altfiles(-dbg)
#
PKG_${PN} = "${PN}"
PKG_${PN}-dbg = "${PN}-dbg"

# The .so has to be installed under /lib for the libc to use it.
#
EXTRA_OECONF = "--datadir=/lib --prefix=/"

inherit autotools

