#!/usr/bin/env bash

ABSPATH=$(readlink -f "$0")
# 현 script의 경로를 찾는다
ABSDIR=$(dirname "$ABSPATH")
# 일종의 import 구문이다
source "${ABSDIR}"/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo ">>> "
    echo ">>> $IDLE_PORT 로 변경합니다."
    echo "set \$service_url http://172.17.0.1:${IDLE_PORT};" | sudo tee /home/centos/dat/conf/nginx/service-url.inc
    docker cp /home/centos/dat/conf/nginx/service-url.inc web:/etc/nginx/conf.d/

    echo ">>> Nginx restart."
    docker restart web
}
