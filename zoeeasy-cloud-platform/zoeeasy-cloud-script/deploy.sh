#!/bin/bash

#部署节点
#集群IP列表,多个用空格分开
DEPLOY_NODE_LIST="wufu-server01"
DEPLOY_NODE_DEV="wufu-server01"
DEPLOY_NODE_TEST="wufu-server05"
#部署环境
DEPLOY_ENV="dev"
#应用部署到的远程服务器目录
REMOTE_DIR="/parking/cloud"
#需要部署的项目名称(需和maven的project名一样,多个用空格分开)
NEED_DEPLOY_PROJECT="zoeeasy-cloud-tool-server
                     zoeeasy-cloud-message-server
                     zoeeasy-cloud-sys-server
                     zoeeasy-cloud-ucc-server
                     zoeeasy-cloud-pms-server
                     zoeeasy-cloud-pds-server
                     zoeeasy-cloud-gather-server
                     zoeeasy-cloud-charge-server
                     zoeeasy-cloud-inspect-server
                     zoeeasy-cloud-order-server
                     zoeeasy-cloud-pay-server
                     zoeeasy-cloud-fina-server
                     zoeeasy-cloud-notify-server
                     zoeeasy-cloud-open-server
                     zoeeasy-cloud-integration-server
                     zoeeasy-cloud-web-inspect
                     zoeeasy-cloud-web-open
                     zoeeasy-cloud-web-platform
                     "
# Date/Time Veriables
LOG_DATE='date "+%Y-%m-%d"'
LOG_TIME='date "+%H:%M:%S"'
CDATE=$(date "+%Y%m%d")
CTIME=$(date "+%H%M%S")
#Shell Env
SHELL_NAME="deploy.sh"
SHELL_DIR="/deploy/log"
SHELL_LOG="${SHELL_DIR}/${SHELL_NAME}.log"
#Code Env
JAR_DIR="/deploy/jar"
CONFIG_DIR="/deploy/config"
LOCK_FILE="/tmp/deploy.lock"

usage(){
    echo$"Usage: $0 [projectJarPath] [ deploy | rollback ]"
}

init() {
    # 测试环境部署
    if [ "$DEPLOY_ENV"x = "test"x ]; then
        DEPLOY_NODE_LIST=${DEPLOY_NODE_TEST}
    else
    #开发环境部署
        DEPLOY_NODE_LIST=${DEPLOY_NODE_DEV}
    fi
    create_dir $SHELL_DIR;
    create_dir $JAR_DIR;
    create_dir $CONFIG_DIR;
}

create_dir() {
    if [ ! -d $1 ]; then
    mkdir -p $1
    fi
}

shell_lock(){
    touch ${LOCK_FILE}
}

shell_unlock(){
    rm -f ${LOCK_FILE}
}

write_log(){
    LOGINFO=$1
    echo "`eval ${LOG_DATE}` `eval ${LOG_TIME}` : ${SHELL_NAME} : ${LOGINFO}"|tee -a ${SHELL_LOG}
}

#拷贝jenkins的工作空间构建的jar包到特定目录,备份,为以后回滚等操作
copy_jar() {
    TARGET_DIR=${JAR_DIR}/${CDATE}${CTIME}
    write_log "Copy jenkins workspace jar file to ${TARGET_DIR}"
    mkdir -p $TARGET_DIR
    for project in $NEED_DEPLOY_PROJECT;do
       mkdir -p $TARGET_DIR/${project}
         echo "`find $1 -name ${project}*.jar` "
         find $1 -name ${project}*.jar -exec cp {}  $TARGET_DIR/${project}/ \;
    done
}

#拷贝应用的jar包到远程服务器
scp_jar(){
    SOURCE_DIR=${JAR_DIR}/${CDATE}${CTIME}
    write_log "Scp jar file to remote machine..."
    for node in $DEPLOY_NODE_LIST; do
        scp -r ${SOURCE_DIR}/* $node:${REMOTE_DIR}
        write_log "Scp to ${node} complete."
    done
}

# 杀掉远程服务器上正在运行的项目
cluster_node_remove(){
    write_log "Kill all runing project on the cluster..."
    for project in $NEED_DEPLOY_PROJECT;do
        for node in $DEPLOY_NODE_LIST;do
            pid=$(ssh $node "ps aux|grep ${project}|grep -v grep|awk '{print $2}'"|awk '{print $2}')
        if [ ! -n "$pid" ]; then
            write_log "${project} is not runing..."
        else
            ssh $node "cd ${REMOTE_DIR}/${project}; ./stop.sh"
            write_log "stop ${project} at ${node}..."
        fi
        done
    done
}

#在远程服务器上启动项目
cluster_deploy(){
    write_log "Up all project on the cluster..."
    for project in $NEED_DEPLOY_PROJECT;do
        for node in $DEPLOY_NODE_LIST;do
            ssh $node "cd ${REMOTE_DIR}/${project}; ./restart.sh"
            write_log "Startup ${project} on $node complete..."
        done
    done
}

#回滚(暂未实现)
rollback(){
    echo rollback
}

#入口
main(){

if [ -f ${LOCK_FILE} ];then
    write_log "Deploy is running"
     #&;&;
    exit;
fi

WORKSPACE=$1
DEPLOY_METHOD=$2
DEPLOY_ENV=$3
init;

echo "DEPLOY_ENV: $DEPLOY_ENV"
echo "DEPLOY_NODE_LIST: $DEPLOY_NODE_LIST"

case $DEPLOY_METHOD in
    deploy)
        shell_lock;
        copy_jar $WORKSPACE;
        cluster_node_remove;
        scp_jar;
        cluster_deploy;
        shell_unlock;
       ;;
    rollback)
        shell_lock;
        rollback;
        shell_unlock;
    ;;
    *)
    usage;
esac
}

main $1 $2 $3
