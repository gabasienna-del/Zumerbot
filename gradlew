#!/bin/sh
DIRNAME=$(dirname "$0")
exec java -jar "$DIRNAME/gradle/wrapper/gradle-8.4/bin/gradle.jar" "$@"
