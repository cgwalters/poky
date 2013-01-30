require llvm.inc

PR = "${INC_PR}.2"

PARALLEL_MAKE_virtclass-native = ""

# Build without -ftree-pre as a workaround for clang segfaulting on x86_64.
# https://bugzilla.redhat.com/show_bug.cgi?id=791365
OECMAKE_C_FLAGS_RELEASE += " -fno-tree-pre"
OECMAKE_CXX_FLAGS += " -fno-tree-pre"

EXTRA_OECMAKE = "\
  -DLLVM_TABLEGEN=${STAGING_BINDIR_NATIVE}/llvm-tblgen \
  -DLLVM_TARGETS_TO_BUILD="${LLVM_ARCH}" \
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
"

LLVM_RELEASE = "3.1"

SRC_URI[md5sum] = "16eaa7679f84113f65b12760fdfe4ee1"
SRC_URI[sha256sum] = "1ea05135197b5400c1f88d00ff280d775ce778f8f9ea042e25a1e1e734a4b9ab"