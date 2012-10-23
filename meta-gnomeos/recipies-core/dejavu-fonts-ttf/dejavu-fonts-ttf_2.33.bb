SUMMARY = "The DejaVu fonts - TTF Edition"
DESCRIPTION = "The DejaVu fonts are a font family based on the \
Vera Fonts. Its purpose is to provide a wider range of characters \
while maintaining the original look and feel through the process \
of collaborative development (see authors), under a Free license."
SECTION = "x11/fonts"
LICENSE = "Bitstream_Vera"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9f867da7a73fad2715291348e80d0763"
PR = "r2"
RDEPENDS_${PN} = "fontconfig-utils"

inherit allarch

SRC_URI = "${SOURCEFORGE_MIRROR}/dejavu/${PN}-${PV}.tar.bz2"

do_install () {
        install -d -m 0755 "${D}${prefix}/share/fonts"
        install -d -m 0755 "${D}${prefix}/share/fonts/ttf/"
        for i in ttf/*.ttf; do
                install -m 644 $i "${D}${prefix}/share/fonts/${i}"
        done

        install -d -m 0755 "${D}${sysconfdir}/fonts"
        install -m 644 fontconfig/*.conf "${D}${sysconfdir}/fonts/"

        install -d -m 0755 "${D}${prefix}/share/doc/${PN}/"
        for i in *.txt README AUTHORS NEWS; do
                install -m 644 "$i" "${D}${prefix}/share/doc/${PN}/$i"
        done
}

pkg_postinst_${PN} () {
#!/bin/sh
fc-cache
}


FILES_${PN} = "/etc ${datadir}/fonts"

SRC_URI[md5sum] = "8b601e91725b6d69141b0fcf527948c0"
SRC_URI[sha256sum] = "82a5823a270715913af51915cc20594568f57afb7450abb989695d8808a4194d"
