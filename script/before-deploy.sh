#!/usr/bin/env bash

# CodeDeploy를 실행하다 대상 디렉토리 안에 배포관련 파일이 있으면 이미 파일이 존재한다고 에러가 발생하며 배포에 실패한다.
# 따라서 폴더 째로 삭제한 다음 다시 생성한다.
if [ -d /var/app/springboot2-aws/zip ]; then
    sudo rm -rf /var/app/springboot2-aws/zip
fi
sudo mkdir -vp /var/app/springboot2-aws/zip
