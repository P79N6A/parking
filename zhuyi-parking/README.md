zhuyi-parking
===========

> 逐一智慧停车平台


# 项目结构

```shell
zhuyi-parking
├── zhuyi-parking-migrate      
├── config                     
├── zhuyi-parking-modules 
│   ├── zhuyi-parking-common   
│   ├── zhuyi-parking-tool                    
│   │        ├── zhuyi-parking-tool-api                   
│   │        ├── zhuyi-parking-tool-api-java                
│   │        ├── zhuyi-parking-tool-dao                  
│   │        ├── zhuyi-parking-tool-server     
│   ├── zhuyi-parking-authz                    
│   │        ├── zhuyi-parking-authz-api                   
│   │        ├── zhuyi-parking-authz-api-java                
│   │        ├── zhuyi-parking-authz-core                 
│   │        ├── zhuyi-parking-authz-dao                  
│   │        ├── zhuyi-parking-authz-server               
│   ├── zhuyi-parking-pay 
│             ├── zhuyi-parking-pay-dao 
│             ├── zhuyi-parking-pay-api
│             ├── zhuyi-parking-pay-api-java
              ├── zhuyi-parking-pay-alipay
              ├── zhuyi-parking-pay-wechat 
              ├── zhuyi-parking-pay-server
│   ├── zhuyi-parking-pms 
              ├── zhuyi-parking-pms-dao 
              ├── zhuyi-parking-pms-api
              ├── zhuyi-parking-pms-api-java
              ├── zhuyi-parking-pms-alipay
              ├── zhuyi-parking-pms-server              
│   ├── zhuyi-parking-schedule
              ├── zhuyi-parking-schedule-dao 
              ├── zhuyi-parking-schedule-api
              ├── zhuyi-parking-schedule-jobclient
              ├── zhuyi-parking-schedule-tasktracker
│   ├── zhuyi-parking-shiro                  
│   ├── zhuyi-parking-ucc
              ├── zhuyi-parking-ucc-dao
              ├── zhuyi-parking-ucc-api
              ├── zhuyi-parking-ucc-api-java
              ├── zhuyi-parking-ucc-server
│ 
├── zhuyi-parking-web            
│   └── zhuyi-parking-common-web               
│   ├── zhuyi-parking-ios               
│     ├── zhuyi-parking-platform          


```

# Dubbo端口配置

```shell

zhuyi-sms-server   30961

zhuyi-parking-message-server 30910
zhuyi-parking-authz-server   30920
zhuyi-parking-authz-platform 30921 
zhuyi-parking-ucc-server 30930
zhuyi-parking-pms-server     30940
zhuyi-parking-pay-server     30950
zhuyi-parking-schedule-jobclient 30960
zhuyi-parking-schedule-tasktracker 30970
zhuyi-parking-too-server     30980
zhuyi-parking-integration-server 30900

zhuyi-parking-platform   30990

```