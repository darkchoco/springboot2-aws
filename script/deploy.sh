#!/usr/bin/env bash

REPO=/var/app/springboot2-aws

echo ">>> Copying artifact"

# jar 파일을 zip 디렉토리의 상위 디렉토리로 copy
cp $REPO/zip/*.jar $REPO/

echo ">>> Checking if the app is running"

CURRENT_PID=$(pgrep -fa springboot2-aws | grep jar | awk '{print $1}')
if [ -z "$CURRENT_PID" ]; then
    echo ">>> No app running"
else
    echo ">>> kill -15 $CURRENT_PID"
    kill -15 "$CURRENT_PID"
    sleep 5
fi

echo ">>> Deploying the new app"

# 가장 최근의 jar 이름을 찾는다.
JAR_NAME=$(ls -t "$REPO"/*.jar | head -n 1)

echo ">>> JAR Name: $JAR_NAME"

chmod +x "$JAR_NAME"

echo ">>> Run $JAR_NAME"

# classpath가 붙으면 jar 안에 있는 resource 디렉토리를 기준으로 경로가 생성된다.
nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-prd.properties,/home/centos/dat/conf/springboot2-aws/application-oauth.properties,/home/centos/dat/conf/springboot2-aws/application-prd-db.properties \
  -Dspring.profiles.active=prd \
  "$JAR_NAME" > $REPO/nohup.out 2>&1 &
