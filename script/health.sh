#!/usr/bin/env bash

ABSPATH=$(readlink -f "$0")
# 현 script의 경로를 찾는다
ABSDIR=$(dirname "$ABSPATH")
# 일종의 import 구문이다
source "${ABSDIR}"/profile.sh
source "${ABSDIR}"/switch.sh

IDLE_PORT=$(find_idle_port)

echo ">>> Health Check Start!"
echo ">>> IDLE_PORT: $IDLE_PORT"
echo ">>> curl -s http://172.17.0.1:$IDLE_PORT/profile "
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://172.17.0.1:"${IDLE_PORT}"/profile)
  UP_COUNT=$(echo "${RESPONSE}" | grep -c 'prd')

  if [ "${UP_COUNT}" -ge 1 ]
  then  # $up_count >= 1 ("prd" 문자열이 있는지 검증)
      echo ">>> Health check 성공. Port를 변경하여 restart 합니다..."
      switch_proxy
      break
  else
      echo ">>> Health check의 응답을 알 수 없거나 혹은 실행 상태가 아닙니다."
      echo ">>> Health check: ${RESPONSE}"
  fi

  if [ "${RETRY_COUNT}" -eq 10 ]
  then
    echo ">>> Health check 실패."
    echo ">>> Nginx에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo ">>> Health check 연결 실패. 재시도 중..."
  sleep 10
done
