#!/bin/bash

SCRIPT_DIR=$(readlink -f $(dirname "$0"))
ROOT_DIR=$(dirname ${SCRIPT_DIR})
PROJECT_NAME=$(<${ROOT_DIR}/etc/project-name)

docker run --rm -e SPRING_PROFILES_ACTIVE ${PROJECT_NAME}
