#!/usr/bin/env bash
java -server -Xmx4000m -Xms4000m -Xss512k -XX:+DisableExplicitGC \
     -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled \
     -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods \
     -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 \
     nohup java -jar gp-back-0.0.1-SNAPSHOT.jar >nohup.log 2>&1