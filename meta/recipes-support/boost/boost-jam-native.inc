# The Boost web site provides free peer-reviewed portable
# C++ source libraries. The emphasis is on libraries which
# work well with the C++ Standard Library. The libraries are
# intended to be widely useful, and are in regular use by
# thousands of programmers across a broad spectrum of applications.
DESCRIPTION = "Make system for boost (native)"
HOMEPAGE = "http://www.boost.org/"
SECTION = "devel"
LICENSE = "Boost"
INC_PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c"

SRC_URI = "${SOURCEFORGE_MIRROR}/boost/boost-jam-${PV}.tgz"
S = "${WORKDIR}/boost-jam-${PV}"

inherit native

do_compile() {
	set -ex
	rm -rf bin.*
	./build.sh gcc
}

# This is too terrible - the build script doesn't give any good
# way I can see to find out where the binaries are placed, so
# rely on only one bin.foo directory being created.
do_install () {
	set -ex
	install -d ${D}${bindir}/
	install -c -m 755 bin.*/bjam ${D}${bindir}/
}
