#!/usr/bin/env bash

thrift --gen java sharing_models.thrift
cd gen-java
rm -r ../../airavata-sharing-registry-stubs/src/main/java/org/apache/airavata/sharing/registry/models/*
cp -r org/apache/airavata/sharing/registry/models/ ../../airavata-sharing-registry-stubs/src/main/java/org/apache/airavata/sharing/registry/models/

cd ..
thrift --gen java sharing_cpi.thrift
cd gen-java
rm -r ../../airavata-sharing-registry-stubs/src/main/java/org/apache/airavata/sharing/registry/service/cpi/*
cp -r org/apache/airavata/sharing/registry/service/cpi/ ../../airavata-sharing-registry-stubs/src/main/java/org/apache/airavata/sharing/registry/service/cpi/

cd ..

rm -r gen-java