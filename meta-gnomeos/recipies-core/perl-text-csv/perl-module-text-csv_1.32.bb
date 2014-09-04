SUMMARY = "Perl Text::CSV module"
DESCRIPTION = "comma-separated values manipulator (using XS or PurePerl)"

SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"
RDEPENDS_${PN} += "perl"
PR = "r0"

LIC_FILES_CHKSUM = "file://README;md5=73eadbd0cbdcdbdd9ef36fcef9e7206d"

SRC_URI = "http://search.mcpan.org/CPAN/authors/id/M/MA/MAKAMAKA/Text-CSV-${PV}.tar.gz"

SRC_URI[md5sum] = "f476205648694a64684be2ab29e44e19"
SRC_URI[sha256sum] = "b49fea66d75a1419a76b0b2b30144d97e1f69728928c06ed08405874fd8ff9af"

S = "${WORKDIR}/Text-CSV-${PV}"

inherit cpan

BBCLASSEXTEND = "native"
