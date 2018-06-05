#!/bin/bash
set +e

if [ "$1" != "generate-manifest" ]; then
  exit 10
fi

name="$(echo "$2" | /var/vcap/packages/odb-service-adapter/bin/jq .deployment_name)"

manifest="
---
name: $name
releases:
- name: redis-service-dev2
  version: '0+dev.7'
stemcells:
- alias: only-stemcell
  os: ubuntu-trusty
  version: '3468.21'
instance_groups:
- name: redis-server
  instances: 1
  jobs:
  - name: redis-server
    release: redis-service-dev2
  vm_type: t2.small
  stemcell: only-stemcell
  persistent_disk_type: 10GB
  azs:
  - z1
  networks:
  - name: default
  properties:
    redis:
      maxclients: 10000
      password: supersecret
      persistence: 'yes'
update:
  canaries: 4
  canary_watch_time: 30000-240000
  update_watch_time: 30000-240000
  max_in_flight: 4
tags:
  product: redis
"

echo "$manifest"
exit 0

