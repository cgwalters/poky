require gzip.inc

PR = "r0"

PROVIDES_append_class-native = " gzip-replacement-native"
NATIVE_PACKAGE_PATH_SUFFIX = "/${PN}"

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "da981f86677d58a106496e68de6f8995"
SRC_URI[sha256sum] = "37dfed1a485d53212c43b3fa2a7c7952f09bf5cd86e37121c222341ee1b27847"
