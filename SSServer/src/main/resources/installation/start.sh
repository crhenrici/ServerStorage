#!/usr/bin/env bash
export DB_USER=$1
export DB_PW=$2
export DB_URI=$3
export DB_NAME=$4
java -jar ligb/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=$5