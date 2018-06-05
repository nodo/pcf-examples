#!/bin/bash
set -eu

if [[ $# != 1 ]]; then
  echo "Usage: "
  echo "  ./doit.sh BOSH_ENV"
  exit 1
fi

environment="$1"
bosh -e "$environment" create-release --force --name dummy-adapter
bosh -e "$environment" upload-release --name dummy-adapter
bosh -e "$environment" -d spacebears-broker deploy -l secrets.yml example_manifests/spacebears-manifest.yml
