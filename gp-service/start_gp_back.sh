#!/bin/sh -l

# create by huangbiao at 2019/08/30

# 启动WEB

pid=`ps -ef | grep gp-service-agent1-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n '$pid' ]
then
    echo "停止正在运行的gp-back进程：$pid"
    kill -9 $pid
fi

echo "==================================================================="
echo "                       启动gp-back项目                             "
echo "==================================================================="
pwd
nohup java -jar gp-service-agent1-0.0.1-SNAPSHOT.jar > gpback.out 2>&1 &
tail -f gpback.out

# 如果脚本不能正常执行，请参考下面：
# linux只能执行格式为unix格式的脚本，因为在dos/window下按一次回车键实际上输入的是“回车（CR)”和“换行（LF）”，而Linux/unix下按一次回车键只输入“换行（LF）”，所以修改的sh文件在每行都会多了一个CR，所以Linux下运行时就会报错找不到命令
# 解决办法：
# vim start.sh
# :set ff 查看sh脚本格式
# :set ff=unix 设置脚本格式为unix，回车，保存
