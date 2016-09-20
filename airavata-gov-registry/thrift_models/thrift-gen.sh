#!/usr/bin/env bash

thrift --gen java gov_reg_models.thrift
cd gen-java
rm -r ../../airavata-gov-registry-stubs/src/main/java/org/apache/airavata/gov/registry/models/*
cp -r org/apache/airavata/gov/registry/models/ ../../airavata-gov-registry-stubs/src/main/java/org/apache/airavata/gov/registry/models/

cd ..
thrift --gen java gov_reg_cpi.thrift
cd gen-java
rm -r ../../airavata-gov-registry-stubs/src/main/java/org/apache/airavata/gov/registry/service/cpi/*
cp -r org/apache/airavata/gov/registry/service/cpi/ ../../airavata-gov-registry-stubs/src/main/java/org/apache/airavata/gov/registry/service/cpi/

cd ..

rm -r gen-java