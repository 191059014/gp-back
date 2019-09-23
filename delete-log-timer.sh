#!/usr/bin/env bash

find /data/ -mtime +1 -name "*.out" -exec rm -rf {} \;
echo `date "+%Y-%m-%d %H:%M:%S"` "delete *.out [path:/data/]" >> /timer/timer.log
find /log/gpbatch/ -mtime +1 -name "*.log" -exec rm -rf {} \;
echo `date "+%Y-%m-%d %H:%M:%S"` "delete *.log [path:/log/gpbatch/]" >> /timer/timer.log
echo "=======================================" >> /timer/timer.log