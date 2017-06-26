#!/bin/bash
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
export LANGUAGE=en_US.UTF-8

export PYTHON=/usr/bin/python2.7

export GRADLE_OPTS="-Dorg.gradle.daemon=false"

exec ./gradlew clean build
