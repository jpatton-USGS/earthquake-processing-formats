# documentation.cmake - a script that generates Doxygen documentation for a
# project
#
# PROJECT_NAME is a CMake environment variable that contains the name of the
#   project.
# GENERATE_DOCUMENTATION is a CMake environment variable that sets whether to run
#   Doxygen for this project, defaults to off
# SRCS is a CMake environment variable list that contains the source files
#   to process with Doxygen
# HDRS is a CMake environment variable list that contains the header files
#   to process with Doxygen

# ----- DOXYGEN OPTION ----- #
option(GENERATE_DOCUMENTATION "Runs Doxygen (requires Doxygen installed)" OFF)

# ----- DOXYGEN ----- #
if(GENERATE_DOCUMENTATION)
    # ----- LOOK FOR DOXYGEN ----- #
    find_package(Doxygen)

    # ----- DOWNLOAD DOXYGEN IF NOT FOUND ----- #
    if(NOT DOXYGEN_FOUND)
        MESSAGE(STATUS "Configuring to download Doxygen.")

        if (GIT_CLONE_PUBLIC)
          ExternalProject_Add(
              Doxygen
              GIT_REPOSITORY https://github.com/doxygen/doxygen.git
              GIT_TAG Release_1_8_12
              TIMEOUT 10
              CMAKE_ARGS -DCMAKE_INSTALL_PREFIX=${INSTALL_LOCATION}
          )
        else()
          ExternalProject_Add(
              Doxygen
              GIT_REPOSITORY git@github.com:doxygen/doxygen.git
              GIT_TAG Release_1_8_12
              TIMEOUT 10
              CMAKE_ARGS -DCMAKE_INSTALL_PREFIX=${INSTALL_LOCATION}
          )
        endif()

        set(DOXYGEN_DEPEND "Doxygen")
    endif()

    # generate doxygen configuration file
    set(doxyfile_in ${CMAKE_CURRENT_LIST_DIR}/Doxyfile.in)
    set(doxyfile ${CMAKE_CURRENT_BINARY_DIR}/Doxyfile)
    configure_file(${doxyfile_in} ${doxyfile} @ONLY)

    # run Doxygen always, but wait until the ${INSTALL_LOCATION} has been created
    add_custom_target(${PROJECT_NAME}_doxygen
        ALL
        COMMAND ${DOXYGEN_EXECUTABLE} ${doxyfile}
        WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
        COMMENT "Generating API documentation with Doxygen"
        DEPENDS ${PROJECT_NAME}
        VERBATIM)
endif()
