#!/bin/bash

export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
export LANGUAGE=en_US.UTF-8

export JAVA_HOME="/opt/jdk"

ls -la

exec java $JAVA_OPTS -jar app.jar

