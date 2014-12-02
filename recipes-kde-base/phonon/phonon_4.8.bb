SUMMARY = "The Phonon multimedia library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = " \
	file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
"

inherit kde cmake-lib

DEPENDS += "qtdeclarative"

SRCREV = "e08fdba6b64ad6a09a9dcd6696de4d82f2dd8224"

# REVISIT some PACKAGECONFIG here
EXTRA_OECMAKE += "-DPHONON_BUILD_DEMOS=ON -DPHONON_BUILD_DECLARATIVE_PLUGIN=ON -DPHONON_BUILD_DESIGNER_PLUGIN=OFF -DPHONON_BUILD_PHONON4QT5=ON"

# cross libs / headers
CMAKE_HIDE_ERROR[1] = "phonon4qt5, -S${libdir}/lib, -S${STAGING_LIBDIR}/lib"

FILES_${PN} += "${datadir}/dbus-1"
FILES_${PN}-dev += "${datadir}/${QT_DIR_NAME}/mkspecs ${datadir}/phonon4qt5/buildsystem"