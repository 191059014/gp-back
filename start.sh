#!/bin/sh -l

# create by huangbiao at 2019/08/30

# 启动WEB
function startGpWeb() {
    echo "==================================================================="
    echo "                       启动gp-web项目                             "
    echo "==================================================================="
    pid=`ps -ef | grep "gp-web" | grep -v grep | awk '{print $2}'`
    if [[ -n "$pid" ]]; then
        echo "停止正在运行的gp-web进程：$pid"
        kill -9 $pid
    fi
    cd /data/gp-web
    echo "拉取最新远程分支代码"
    git fetch --all && git reset --hard origin/master && git pull
    nohup cnpm run dev > ../gpweb.out 2>&1 &
    exit
}

# 启动后台
function startGpBack() {
    echo "==================================================================="
    echo "                更新unic项目，并重新打包unic项目                   "
    echo "==================================================================="
    cd /data/unic
    git fetch --all && git reset --hard origin/master && git pull
    mvn clean install

    echo "==================================================================="
    echo "           更新remote-api项目，并重新打包remote-api项目            "
    echo "==================================================================="
    cd /data/remote-api
    git fetch --all && git reset --hard origin/master && git pull
    mvn clean install

    echo "==================================================================="
    echo "              更新gp-back项目，并重新打包gp-back项目               "
    echo "==================================================================="
    cd /data/gp-back
    git fetch --all && git reset --hard origin/master && git pull
    mvn clean install

    pid=`ps -ef | grep gp-service-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
    if [ -n '$pid' ]
    then
        echo "停止正在运行的gp-back进程：$pid"
        kill -9 $pid
    fi

    echo "==================================================================="
    echo "                       启动gp-back项目                             "
    echo "==================================================================="
    cd /data/gp-back/gp-service/target
    pwd
    ls
    nohup java -jar gp-service-0.0.1-SNAPSHOT.jar > ../../../gpback.out 2>&1 &
    tail -f ../../../gpback.out
}

# 启动后台批处理
function startGpBatch() {
    echo "==================================================================="
    echo "                更新unic项目，并重新打包unic项目                   "
    echo "==================================================================="
    cd /data/unic
    git fetch --all && git reset --hard origin/master && git pull
    mvn clean install

    echo "==================================================================="
    echo "           更新remote-api项目，并重新打包remote-api项目            "
    echo "==================================================================="
    cd /data/remote-api
    git fetch --all && git reset --hard origin/master && git pull
    mvn clean install

    echo "==================================================================="
    echo "           更新gp-facade项目，并重新打包gp-facade项目              "
    echo "==================================================================="
    cd /data/gp-back/gp-facade
    git fetch --all && git reset --hard origin/master && git pull
    mvn clean install

    echo "==================================================================="
    echo "              更新gp-batch项目，并重新打包gp-batch项目               "
    echo "==================================================================="
    cd /data/gp-batch
    git fetch --all && git reset --hard origin/master && git pull
    mvn clean install

    pid=`ps -ef | grep gp-batch-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
    if [ -n '$pid' ]
    then
        echo "停止正在运行的gp-batch进程：$pid"
        kill -9 $pid
    fi

    echo "==================================================================="
    echo "                      启动gp-batch项目                             "
    echo "==================================================================="
    cd /data/gp-batch/target
    pwd
    ls
    nohup java -jar gp-batch-0.0.1-SNAPSHOT.jar > ../../gpbatch.out 2>&1 &
    tail -f ../../gpbatch.out
}

echo "请选择要启动的项目"
printf "1: gp-web \n"
printf "2: gp-back \n"
printf "3: gp-batch \n"
read startNo
if [ $startNo = 1 ]; then
    startGpWeb
elif [ $startNo = 2 ]; then
    startGpBack
elif [ $startNo = 3 ]; then
    startGpBatch
else
    echo "错误的选项"
fi

# 如果脚本不能正常执行，请参考下面：
# linux只能执行格式为unix格式的脚本，因为在dos/window下按一次回车键实际上输入的是“回车（CR)”和“换行（LF）”，而Linux/unix下按一次回车键只输入“换行（LF）”，所以修改的sh文件在每行都会多了一个CR，所以Linux下运行时就会报错找不到命令
# 解决办法：
# vim start.sh
# :set ff 查看sh脚本格式
# :set ff=unix 设置脚本格式为unix，回车，保存
