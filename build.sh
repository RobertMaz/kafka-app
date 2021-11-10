#!/usr/bin/env bash

export SERVER=localhost:9092

kubectl delete -f kuber/consumer.yml
kubectl delete -f kuber/producer.yml

sleep 1s

minikube image rm robertmaz/producer
minikube image rm robertmaz/consumer

mvn clean package -f consumer/pom.xml
docker build -t robertmaz/consumer consumer/
docker push robertmaz/consumer

mvn clean package -f producer/pom.xml
docker build -t robertmaz/producer producer/
docker push robertmaz/producer

kubectl apply -f kuber/
