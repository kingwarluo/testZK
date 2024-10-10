#!/bin/bash

# $1 操作（start/stop） $2 项目名

CATALINA_OPTS="-Xms1024m -Xmx1024m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC"
RUN_OPTS="--spring.profiles.active=dev101"

PROJECT_NAME=$2
JAR_NAME=$PROJECT_NAME.jar
LOG_NAME=$PROJECT_NAME.log

if [ "$1" = "" ];
then
    echo -e "\033[0;31m 未输入操作名 \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$2" = "" ];
then
    echo -e "\033[0;31m 未输入应用名 \033[0m"
    exit 1
fi

function start()
{
	count=`ps -ef |grep java|grep $PROJECT_NAME|grep -v grep|wc -l`
	if [ $count != 0 ];then
		echo "$PROJECT_NAME is running..."
	else
		# 启动jar
		echo "启动命令 nohup java -jar $JAR_NAME > logs/$LOG_NAME 2>&1 &"
		echo "Start $PROJECT_NAME success..."
		nohup java -jar $CATALINA_OPTS $JAR_NAME $RUN_OPTS > "logs/$LOG_NAME" 2>&1 &
		PID=$!
		echo "$PROJECT_NAME启动完成，PID=$PID，日志路径 logs/$LOG_NAME"
	fi
}

function stop()
{
	echo "Stop $PROJECT_NAME"
	boot_id=`ps -ef |grep java|grep $PROJECT_NAME|grep -v grep|awk '{print $PROJECT_NAME}'`
	count=`ps -ef |grep java|grep $PROJECT_NAME|grep -v grep|wc -l`

	if [ $count != 0 ];then
	    kill $boot_id
    	count=`ps -ef |grep java|grep $PROJECT_NAME|grep -v grep|wc -l`

		boot_id=`ps -ef |grep java|grep $PROJECT_NAME|grep -v grep|awk '{print $PROJECT_NAME}'`
		kill -9 $boot_id
	else
        echo "$PROJECT_NAME is not running..."
	fi
}

function restart()
{
	stop
	sleep 2
	start
}

function status()
{
    count=`ps -ef |grep java|grep $PROJECT_NAME|grep -v grep|wc -l`
    if [ $count != 0 ];then
        echo "$PROJECT_NAME is running..."
    else
        echo "$PROJECT_NAME is not running..."
    fi
}

case $1 in
	start)
	start;;
	stop)
	stop;;
	restart)
	restart;;
	status)
	status;;
	*)

	echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh  $0  {start|stop|restart|status}  \033[0m
\033[0;31m Example: \033[0m
	  \033[0;33m sh  $0  start \033[0m"
esac
