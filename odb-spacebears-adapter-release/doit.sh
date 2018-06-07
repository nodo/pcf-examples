#!/bin/bash
set -eu

if [[ $# != 1 ]]; then
  echo "Usage: "
  echo "  ./doit.sh BOSH_ENV"
  exit 1
fi

environment="$1"

## Build adapter source code and upload jar
javac src/github.com/pcf-examples/odb-spacebears-adapter/Main.java
jar -f /tmp/adapter.jar -c src/github.com/pcf-examples/odb-spacebears-adapter/*class
bosh add-blob /tmp/adapter.jar adapter/adapter.jar
bosh -e "$environment" upload-blobs

## Create adapter release
bosh -e "$environment" create-release --force --name dummy-adapter
bosh -e "$environment" upload-release --name dummy-adapter

## Deploy ODB
bosh -e "$environment" -d spacebears-broker deploy -l secrets.yml example_manifests/spacebears-manifest.yml
