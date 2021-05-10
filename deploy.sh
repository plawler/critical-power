#!/bin/bash
./gradlew dockerBuildNative
docker build . -t criticalpower -f build/docker/DockerfileNative
docker run --rm --entrypoint cat criticalpower /function/function.zip > build/function.zip
