SUMMARY = "GPS Multiplexing Daemon"
DESCRIPTION = "Gypsy is a GPS multiplexing daemon which allows \
multiple clients to access GPS data from multiple GPS sources \
concurrently.  Gypsy also hides the details of parsing NMEA from the \
client applications, passing the data as simple values for the clients \
to use."
LICENSE = "GPL"
SECTION = "x11"
DEPENDS = "glib-2.0 dbus bluez4 dbus-glib"

inherit autotools pkgconfig

EXTRA_OECONF += "--with-distro=debian"

do_configure_prepend () {
	touch ${S}/gtk-doc.make
}

FILES_${PN} += "/usr/share/dbus-1/services/"
