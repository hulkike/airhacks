#!/bin/sh
mvn clean package && docker build -t com.airhacks/boring .
docker rm -f boring || true && docker run -d -p 8080:8080 -p 4848:4848 --name boring com.airhacks/boring 
