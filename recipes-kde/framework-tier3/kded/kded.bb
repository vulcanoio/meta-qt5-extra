SUMMARY = "Extensible deamon for providing system level services"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = " \
	file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
"

require ../../kf5-version.inc

inherit kde cmake-lib

DEPENDS += "kconfig kcoreaddons kcrash kdbusaddons kdoctools kinit kservice"

SRCREV = "a72c1fcbf6600c1c84e71c312a586386dcd7ce62"

# cross libs / headers
CMAKE_HIDE_ERROR[1] = "KDED, -S${libdir}/lib, -S${STAGING_LIBDIR}/lib"
CMAKE_HIDE_ERROR[2] = "KDED, -S${includedir}, -S${STAGING_INCDIR}"

# executables REVISIT -> native 
CMAKE_HIDE_ERROR[3] = "KDED, -S${bindir}, -S${STAGING_BINDIR}"

FILES_${PN} += "${datadir}/dbus-1 ${datadir}/k*5 ${libdir}/libkdeinit5_kded5.so"
FILES_${PN}-dev = "${libdir}/cmake"