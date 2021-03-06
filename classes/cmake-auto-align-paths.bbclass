# do_configure_append_class-cross does not work so hack
do_configure_prepend_class-native() {
    no_cmake_auto_align_paths=true
}

do_configure_prepend_class-nativesdk() {
    no_cmake_auto_align_paths=true
}

do_configure_append() {
    # remove absolute paths from exported cmake files
    for f in `find ${B}/CMakeFiles/Export -name '*.cmake'` ${CMAKE_ADD_ALIGN_FILES} ; do
        sed -i 's:${STAGING_INCDIR_NATIVE}:${includedir}:g' "$f"
        sed -i 's:${STAGING_INCDIR}:${includedir}:g' "$f"
        sed -i 's:${STAGING_LIBDIR_NATIVE}:${libdir}:g' "$f"
        sed -i 's:${STAGING_LIBDIR}:${libdir}:g' "$f"
    done
}

