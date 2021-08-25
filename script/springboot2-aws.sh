#!/bin/bash

export JAVA_HOME="/usr/lib/jvm/java-11-openjdk"
export PATH=/usr/lib/jvm/java-11-openjdk/bin:$PATH

# classpath가 붙으면 jar 안에 있는 resource 디렉토리를 기준으로 경로가 생성된다.
nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-prd.properties,/home/centos/dat/conf/springboot2-aws/application-oauth.properties,/home/centos/dat/conf/springboot2-aws/application-prd-db.properties \
  -Dspring.profiles.active=prd \
  /var/jenkins_share/springboot2-aws-0.0.1-SNAPSHOT.jar > /home/centos/tmp/nohup.out 2>&1 &
