SUMMARY = "Baloo provides searching and indexing infrastucture with an emphasis on files"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
	file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
	file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

inherit kde-plasma cmake-lib

DEPENDS += "ki18n kconfig kidletime kitemmodels kinit kauth kcrash solid kio kdelibs4support kfilemetadata ktexteditor xapian-core"

PV = "5.6.1"
SRC_URI[md5sum] = "df0d96ef85bfe46bceab6fdf796300f8"
SRC_URI[sha256sum] = "c2e1b6bf3c2ad830fde63c863fa1b792272cca2986a5145b490ba63ef116eea9"

SRC_URI += "file://0001-fix-build-for-QT_NO_SESSIONMANAGER.patch"

# cross libs / headers
CMAKE_HIDE_ERROR[1] = "KF5Baloo, -S${libdir}/lib, -S${STAGING_LIBDIR}/lib"
CMAKE_HIDE_ERROR[2] = "KF5Baloo, -S${includedir}, -S${STAGING_INCDIR}"

FILES_${PN} += "${datadir} \
	${libdir}/qml/org/kde/baloo \
	${libdir}/plugins ${libdir}/kauth \
"
FILES_${PN}-dbg += " \
	${libdir}/qml/org/kde/baloo/.debug \
	${libdir}/*/.debug \
	${libdir}/plugins/kf5/*/.debug \
"

