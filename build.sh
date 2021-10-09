#!/usr/bin/env bash

export SERVER=localhost:9092

cd consumer
sh ./mvnw clean package
docker build -t consumer .

cd ../producer
sh ./mvnw clean package
docker build -t producer .

cd ../
docker-compose up
