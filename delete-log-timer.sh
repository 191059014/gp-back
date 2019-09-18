#!/usr/bin/env bash
cd /data/
rm -f gpback.out gpweb.out gpbatch.out
echo `date "+%Y-%m-%d %H:%M:%S"` "delete gpback.out gpweb.out gpbatch.out [path:/data/]" >> /timer/timer.log
find /log/ -mtime +3 -name "gp*.log*" -exec rm -rf {} \
echo `date "+%Y-%m-%d %H:%M:%S"` "删除3天前的日志文件 [path:/log/]" >> /timer/timer.log
echo "=======================================" >> /timer/timer.log