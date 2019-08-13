#!/bin/sh -l
pid=`ps -ef | grep gp-back-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n '$pid' ]
then
   echo "stop gp-back..."
   kill -9 $pid
   echo "start gp-back..."
   nohup java -jar gp-back-0.0.1-SNAPSHOT.jar > nohup.out 2>&1 &
else
   echo "start gp-back..."
   nohup java -jar gp-back-0.0.1-SNAPSHOT.jar > nohup.out 2>&1 &
fi
# linux只能执行格式为unix格式的脚本
# 因为在dos/window下按一次回车键实际上输入的是“回车（CR)”和“换行（LF）”，而Linux/unix下按一次回车键只输入“换行（LF）”，所以修改的sh文件在每行都会多了一个CR，所以Linux下运行时就会报错找不到命令
# vim restart.sh
# :set ff 查看sh脚本格式
# :set ff=unix 设置脚本格式为unix