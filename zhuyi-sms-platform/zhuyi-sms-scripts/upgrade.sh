#!/usr/bin/env bash
#升级脚本
#需要升级文件夹和其他项目文件夹在同一个文件夹下
#需要升级的项目名称(文件目录和项目名称一样,多个用空格分开)
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
                     zoeeasy-cloud-integration-server
                     zoeeasy-cloud-job-server"

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

# 杀掉远程服务器上正在运行的项目
cluster_node_shutdown(){
    write_log "Kill all runing project on the cluster..."
    for project in $NEED_DEPLOY_PROJECT;do
           pid=$(ps aux|grep ${project}|grep -v grep|awk '{print $2}'|awk '{print $2}')
            if [ ! -n "$pid" ]; then
                write_log "${project} is not runing..."
            else
              ssh $node "cd ${REMOTE_DIR}/${project} & ./stop.sh & tail -20f out.nohup;"
              write_log "stop ${project} at ${node}..."
            fi
    done
}

#入口
main(){

    if [ -f ${LOCK_FILE} ];then
        write_log "Deploy is running" &
        exit;
    fi

WORKSPACE=$1
DEPLOY_METHOD=$2
init;

case $DEPLOY_METHOD in
    deploy)
        shell_lock;
        cluster_node_shutdown;
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

main $1 $2