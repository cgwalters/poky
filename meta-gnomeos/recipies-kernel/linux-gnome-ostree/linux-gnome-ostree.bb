inherit kernel

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git"

SRC_URI += " file://config-generic \
	     file://config-nodebug \
	     file://config-x86_64-generic \
	     file://config-x86-generic \
	     "

LINUX_VERSION ?= "3.6"
LINUX_VERSION_EXTENSION ?= "-gnome-ostree"

# tag: v3.6
SRCREV="a0d271cbfed1dd50278c6b06bead3d00ba0a88f9"

S = "${WORKDIR}/git"

PR = "r2"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "(atom-pc|qemux86-64)"

do_genconfig() {
        export KERNEL_ARCH=${TARGET_ARCH}
	case ${KERNEL_ARCH} in
	  i586|i686) KERNEL_ARCH=x86
	esac
	cat ${WORKDIR}/config-generic \
	    ${WORKDIR}/config-nodebug \
	    ${WORKDIR}/config-${KERNEL_ARCH}-generic \
	    > ${B}/.config
	echo "# Global settings from linux recipe" >> ${B}/.config
	echo "CONFIG_LOCALVERSION="\"${LINUX_VERSION_EXTENSION}\" >> ${B}/.config
}

addtask genconfig before do_configure after do_patch

do_strip_modules() {
  find "${D}" -type f | (while read fname; do
    if file ${fname} | grep -q 'ELF.*not stripped'; then
      echo "stripping ${fname}"
      ${STRIP} --strip-debug --remove-section=.comment --remove-section=.note --preserve-dates "${fname}"
    fi
  done)
}

addtask strip_modules before do_deploy after do_install
