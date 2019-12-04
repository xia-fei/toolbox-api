#!/bin/bash
mvn clean package -Dmaven.test.skip=true

JVM_OPS="-Dserver.port=8002 -Xmx64m"

APP_NAME=$(ls target|grep .*jar$ );
PID=$(ps -ef |grep $APP_NAME |grep -v grep |awk '{print $2}');

echo " ----------------------------------------"
echo "|  APP_NAME:"$APP_NAME"  PID:"$PID
echo " ----------------------------------------"

kill -9 $PID

nohup  java $JVM_OPS -jar  target/$APP_NAME   2>&1 &


tail -f nohup.out

