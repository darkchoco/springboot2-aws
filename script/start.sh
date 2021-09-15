#!/usr/bin/env bash

ABSPATH=$(readlink -f "$0")
# 현 script의 경로를 찾는다
ABSDIR=$(dirname "$ABSPATH")
# 일종의 import 구문이다
source "${ABSDIR}"/profile.sh

REPO=/var/app/springboot2-aws/dist

echo ">>> Copying artifact."
echo ">>> cp $REPO/zip/*.jar $REPO/"
cp $REPO/zip/*.jar $REPO/

echo ">>> 새 어플리케이션 배포."
# shellcheck disable=SC2012
JAR_NAME=$(ls -tr $REPO/*.jar | tail -n 1)

echo ">>> JAR Name: $JAR_NAME"
echo ">>> $JAR_NAME 에 실행권한 추가."
chmod +x "$JAR_NAME"

IDLE_PROFILE=$(find_idle_profile)
echo ">>> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-"$IDLE_PROFILE".properties,/home/centos/dat/conf/springboot2-aws/application-oauth.properties,/home/centos/dat/conf/springboot2-aws/application-prd-db.properties \
    -Dspring.profiles.active="$IDLE_PROFILE" \
    "$JAR_NAME" > $REPO/nohup.out 2>&1 &
