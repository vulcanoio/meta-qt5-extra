require greenisland.inc

inherit cmake-lib cmake-auto-align-paths

DEPENDS += " \
    ${BPN}-native \
    ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
    wayland \
    qtwayland \
    libkscreen \
    virtual/egl \
    libinput \
"
SRC_URI = " \
    git://github.com/greenisland/${BPN}.git;protocol=git;branch=master \
    file://0001-find-native-greenisland-wayland-scanner.patch \
    file://0002-RaspberryPi-Don-t-suggest-closed-source-gles-driver-.patch \
"

# we don't have wayland-protocols yet - revisit
EXTRA_OECMAKE += "-DUSE_LOCAL_WAYLAND_PROTOCOLS=ON"

do_compile_append() {
    for f in `find ${B} -name 'GreenIsland*.cmake'` ; do
        sed -i 's:${STAGING_INCDIR}:${includedir}:g' "$f"
        sed -i 's:${STAGING_LIBDIR}:${libdir}:g' "$f"
    done
}


PACKAGECONFIG ??= " \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", "xwayland", "",d)} \
"
PACKAGECONFIG[xwayland] = "-DENABLE_XWAYLAND=ON,-DENABLE_XWAYLAND=OFF,libxcb"

# cross libs / headers
CMAKE_ALIGN_SYSROOT[1] = "GreenIsland, -S${libdir}/lib, -S${STAGING_LIBDIR}/lib"
CMAKE_ALIGN_SYSROOT[2] = "GreenIsland, -S${includedir}, -S${STAGING_INCDIR}"

FILES_${PN} += " \
    ${OE_QMAKE_PATH_PLUGINS} \
    ${OE_QMAKE_PATH_QML}/GreenIsland \
"

FILES_${PN}-dbg += " \
    ${OE_QMAKE_PATH_PLUGINS}/.debug \
    ${OE_QMAKE_PATH_QML}/GreenIsland/.debug \
"

FILES_${PN}-dev += " \
    ${libdir}/cmake \
    ${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs \
"
