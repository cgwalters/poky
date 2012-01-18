DESCRIPTION = "An sh-compatible command language interpreter."
HOMEPAGE = "http://cnswww.cns.cwru.edu/~chet/bash/bashtop.html"
SECTION = "base/shell"

# GPLv2+ (< 4.0), GPLv3+ (>= 4.0)
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "ncurses bison-native"

inherit autotools gettext update-alternatives

PARALLEL_MAKE = ""

bindir = "/bin"
sbindir = "/sbin"

# Override getcwd_malloc because the copy of getcwd()
# in bash blows up when run inside a chroot and a bind
# mount pointing outside.  This assumes we're using eglibc.
EXTRA_OECONF = "--enable-job-control bash_cv_getcwd_malloc=yes "

export CC_FOR_BUILD = "${BUILD_CC}"

ALTERNATIVE_NAME = "sh"
ALTERNATIVE_PATH = "${base_bindir}/bash"
ALTERNATIVE_LINK = "${base_bindir}/sh"
ALTERNATIVE_PRIORITY = "100"

export AUTOHEADER = "true"

do_configure_prepend () {
	if [ ! -e acinclude.m4 ]; then
		cat aclocal.m4 > acinclude.m4
	fi
}

pkg_postinst_${PN} () {
	touch $D${sysconfdir}/shells
	grep -q "bin/bash" $D${sysconfdir}/shells || echo /bin/bash >> $D${sysconfdir}/shells
	grep -q "bin/sh" $D${sysconfdir}/shells || echo /bin/sh >> $D${sysconfdir}/shells
}
