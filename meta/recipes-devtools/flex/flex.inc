SUMMARY = "Flex (The Fast Lexical Analyzer)"
DESCRIPTION = "Flex is a fast lexical analyser generator.  Flex is a tool for generating programs that recognize \
lexical patterns in text."
HOMEPAGE = "http://sourceforge.net/projects/flex/"

SECTION = "devel"
LICENSE = "BSD"

SRC_URI = "${SOURCEFORGE_MIRROR}/flex/flex-${PV}.tar.bz2 "

inherit autotools gettext

# We don't want to hardcode the m4 path since it will be found
# in the staging directory.
EXTRA_OECONF = " ac_cv_path_M4=m4"

