#!/bin/bash
mvn clean package -Dmaven.test.skip=true
java -jar target/customer-api-1.0.1.jar