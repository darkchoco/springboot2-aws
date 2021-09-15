#!/usr/bin/env bash

# 다른 script에서 공통으로 사용할 'profile'과 port check logic.
# bash는 return value가 안되니 제일 마지막 줄에 echo로 해서 결과 출력 후, 클라이언트에서 값을 사용한다.

# 쉬고 있는 profile 찾기: prd1이 사용중이면 prd2가 쉬고 있고, 반대면 prd1이 쉬고 있음
function find_idle_profile()
{
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://ec2-99-80-33-183.eu-west-1.compute.amazonaws.com/profile)

    if [ "${RESPONSE_CODE}" -ge 400 ] # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)
    then
        CURRENT_PROFILE=prd2
    else
        CURRENT_PROFILE=$(curl -s http://ec2-99-80-33-183.eu-west-1.compute.amazonaws.com/profile)
    fi

    if [ "${CURRENT_PROFILE}" == prd1 ]
    then
      IDLE_PROFILE=prd2
    else
      IDLE_PROFILE=prd1
    fi

    echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ "${IDLE_PROFILE}" == prd1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}
