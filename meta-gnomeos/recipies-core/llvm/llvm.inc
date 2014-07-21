DESCRIPTION = "The Low Level Virtual Machine"
HOMEPAGE = "http://llvm.org"
# 3-clause BSD-like
LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=71df953c321b5b31ae94316a7fb6a7f3"

DEPENDS = "llvm-native"
DEPENDS_virtclass-native = "cmake-native"

INC_PR = "r1"

SRC_URI = "http://llvm.org/releases/${PV}/llvm-${PV}.src.tar.gz"

S = "${WORKDIR}/llvm-${PV}.src"

inherit cmake perlnative pythonnative

# Defines the LLVM supported arches. By now we always build either for ${BUILD}
# (native) or ${TARGET}. In the future it may make sense to enable all backends
# for the non-native build. The decision which backends are used is made by
# the 3rd party program or library that uses llvm anyway.
LLVM_ARCH = "${@get_llvm_arch(d)}"

# This is used for generating the install directory for the llvm libraries,
# binaries and headers. It makes side by side installation of those possible.
LLVM_RELEASE = "${PV}"

# llvm *must* be built out of tree
OECMAKE_SOURCEPATH = "${S}"
OECMAKE_BUILDPATH = "../build-${LLVM_ARCH}"
EXTRA_OECMAKE = "\
  -DLLVM_TABLEGEN=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
  -DLLVM_TARGETS_TO_BUILD=${LLVM_ARCH} \
  -DCMAKE_LINKER:FILEPATH=${LD} \
  -DCMAKE_AR:FILEPATH=${AR} \
  -DCMAKE_OBJCOPY:FILEPATH=${OBJCOPY} \
  -DCMAKE_OBJDUMP:FILEPATH=${OBJDUMP} \
  -DCMAKE_RANLIB:FILEPATH=${RANLIB} \
  -DCMAKE_STRIP:FILEPATH=${STRIP} \
  -DNM_PATH:FILEPATH=${NM} \
  -DLLVM_ENABLE_PIC:BOOL=ON \
  -DLLVM_TARGET_ARCH:STRING=${LLVM_ARCH} \
  -DLLVM_ENABLE_ASSERTIONS:BOOL=ON \
  -DCMAKE_BUILD_TYPE:STRING=RelWithDebInfo \
  -DBUILD_SHARED_LIBS:BOOL=ON \
  -C ${OECMAKE_BUILDPATH} \
"
# We need to reset this to avoid breakage as we build out of tree
TOOLCHAIN_OPTIONS = ""

# the difference to the non-native build is that we do not need
# to declare the location of the tblgen executable.
EXTRA_OECMAKE_virtclass-native = "\
  -DLLVM_TARGETS_TO_BUILD=${LLVM_ARCH} \
  -DCMAKE_LINKER:FILEPATH=${LD} \
  -DCMAKE_AR:FILEPATH=${AR} \
  -DCMAKE_OBJCOPY:FILEPATH=${OBJCOPY} \
  -DCMAKE_OBJDUMP:FILEPATH=${OBJDUMP} \
  -DCMAKE_RANLIB:FILEPATH=${RANLIB} \
  -DCMAKE_STRIP:FILEPATH=${STRIP} \
  -DNM_PATH:FILEPATH=${NM} \
"

PACKAGES_virtclass-native = ""

PACKAGES_DYNAMIC_virtclass-native = ""

FILES_${PN} = " \
            ${libdir}/lib*.so \
            "

FILES_${PN}-dbg += "${libdir}/llvm/.debug ${bindir}/llvm/.debug"

FILES_${PN}-dev = " \
                   ${includedir} \
                   ${bindir}/* \
                   ${libdir}/llvm/LLVMHello.so \
                   ${libdir}/llvm/BugpointPasses.so \
                   ${libdir}/llvm/*.a \
                 "

do_install() {
    cd ${OECMAKE_BUILDPATH}			      
    oe_runmake DESTDIR=${D} install
}

do_install_virtclass-native() {
    cd ${OECMAKE_BUILDPATH}			      
    oe_runmake DESTDIR=${D} install
}

# Retrieve the target in a way that is compatible to the arch
# value in llvm (>= 2.5)
def get_llvm_arch(d):
    import bb;

    arch = bb.data.getVar('TARGET_ARCH', d, 1)
    if arch == "x86_64" or arch == "i486" or arch == "i586" or arch == "i686":
        arch = "X86"
    elif arch == "arm":
        arch = "ARM"
    elif arch == "mipsel" or arch == "mips":
        arch = "mips"
    elif arch == "powerpc" or arch == "powerpc64":
        arch = "PowerPC"
    else:
        bb.error("%s does not support %s yet" % (bb.data.getVar('PN', d, 1), arch) );

    return arch

BBCLASSEXTEND = "native"

