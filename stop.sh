#!/usr/bin/env bash
echo "Stopping SpringBoot Application"
pid=`ps -ef | grep gp-back.jar | grep -v grep | awk '{print $2}'`
if [[ -n "$pid" ]]
then
   kill -9 $pid
fi