#!/bin/sh -l
echo "Stopping SpringBoot Application..."
pid=`ps -ef | grep gp-back-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
   kill -9 $pid
   echo "Prepare Restart Application..."
   nohup java -jar gp-back-0.0.1-SNAPSHOT.jar > nohup.out 2>&1 &
else
   echo "Prepare Start Application..."
   nohup java -jar gp-back-0.0.1-SNAPSHOT.jar > nohup.out 2>&1 &
fi