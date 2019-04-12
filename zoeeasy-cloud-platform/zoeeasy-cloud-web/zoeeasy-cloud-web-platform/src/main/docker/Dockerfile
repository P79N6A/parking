# Dockerfile for apollo-configservice
# Build with:
# docker build -t apollo-configservice .
# Run with:
# docker run -p 8080:8080 -d -v /tmp/logs:/opt/logs --name apollo-configservice apollo-configservice

FROM openjdk:8-jre-alpine
MAINTAINER cacotopia <cacotopia@126.com>

ENV VERSION 1.1.0-SNAPSHOT

RUN echo "http://mirrors.aliyun.com/alpine/v3.8/main" > /etc/apk/repositories \
    && echo "http://mirrors.aliyun.com/alpine/v3.8/community" >> /etc/apk/repositories \
    && apk update upgrade \
    && apk add --no-cache procps unzip curl bash tzdata \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

ADD zoeeasy-cloud-web-platform-assembly.tar.gz /zoeeasy-cloud-web-platform/zoeeasy-cloud-web-platform-assembly.tar.gz

RUN tar -zxvf /zoeeasy-cloud-web-platform/zoeeasy-cloud-web-platform-assembly.tar.gz -d /zoeeasy-cloud-web-platform \
    && rm -rf /zoeeasy-cloud-web-platform/zoeeasy-cloud-web-platform-assembly.tar.gz \
    && sed -i '$d' /zoeeasy-cloud-web-platform/restartup.sh \
    && echo "tail -f /dev/null" >> /zoeeasy-cloud-web-platform/restartup.sh

EXPOSE 8080

CMD ["/zoeeasy-cloud-web-platform/restartup.sh"]