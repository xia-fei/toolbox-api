#!/bin/bash
basePath=/root/xiafei
cd ${basePath}
#所有后端接口 8001
git clone https://github.com/xia-fei/toolbox-api.git
#所有前端项目
git clone https://github.com/xia-fei/toolbox-web.git
#文件服务器 8002
git clone https://github.com/xia-fei/file-server.git
#聊天服务器 8003
git clone https://github.com/xia-fei/web-chat-room.git
#个人博客
git clone https://github.com/xia-fei/xia-fei.github.io.git

killProcess(){
	echo 'will kill process  pid->'$1`ps -ef | grep file-0.0 |grep -v grep |awk '{print $2}'`
    ps -ef | grep $1 |grep -v grep |awk '{print $2}'|xargs kill
}

#后端接口项目部署 8001
toolbox-api(){
killProcess api-0.0.1-SNAPSHOT.jar
cd ${basePath}/toolbox-api && \
git pull && \
mvn package -Dmaven.test.skip=true && \
nohup java -Dserver.port=8001 -jar target/api-0.0.1-SNAPSHOT.jar &
}
#文件服务器部署 8002
image-server(){
killProcess file-0.0.1-SNAPSHOT.jar
cd ${basePath}/file-server && \
git pull && \
mvn package -Dmaven.test.skip=true && \
nohup java -Daccess.key.secret="QGWGUdG8wxbNTz1QCQ05SbNifXXZ7n" -Daccess.key.id="LTAIX3Mkmhdk2GeB" -Dserver.port=8002 -jar target/file-0.0.1-SNAPSHOT.jar &
}

#聊天服务器部署 8003
m-server(){
killProcess cloud-0.0.1-SNAPSHOT.jar
cd ${basePath}/web-chat-room && \
git pull && \
mvn package -Dmaven.test.skip=true && \
nohup java -Dserver.port=8003 -jar target/cloud-0.0.1-SNAPSHOT.jar&
}



#部署个人主页
www-web(){
cd ${basePath}/xia-fei.github.io && \
git pull && \
rm -rf /usr/share/nginx/xia-fei.github.io && \
cp -r /root/xiafei/xia-fei.github.io /usr/share/nginx/xia-fei.github.io
}

sql-web(){
cd ${basePath}/toolbox-web/sql-resolve && \
npm run build && \
rm -rf /usr/share/nginx/sql-resolve && \
cp -rf /root/xiafei/toolbox-web/sql-resolve/dist /usr/share/nginx/sql-resolve
}

#部署notebook
note-web(){
cd  ${basePath}/toolbox-web/notebook && \
git pull && \
npm run build && \
rm -rf /usr/share/nginx/notebook && \
mv /root/xiafei/toolbox-web/notebook/dist /usr/share/nginx/notebook
}

all(){
toolbox-api
image-server
m-server
www-web
sql-web
note-web
}
$1
