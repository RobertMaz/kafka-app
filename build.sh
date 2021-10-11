#!/usr/bin/env bash

export SERVER=localhost:9092

mvn clean package -f consumer/pom.xml
docker build -t robertmaz/consumer consumer/

mvn clean package -f producer/pom.xml
docker build -t robertmaz/producer producer/

#docker-compose up
