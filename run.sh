#!/bin/sh
java -server -Xmx2048m -Xms1024m -Xss512k -XX:+DisableExplicitGC \
     -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled \
     -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods \
     -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 \
     -jar gp-back.jar