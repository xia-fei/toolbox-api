www.xia-fei.com  
images.xia-fei.com  
sql.xia-fei.com  
note.xia-fei.com  
m.xia-fei.com  

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

#后端接口项目部署
cd /root/xiafei/toolbox-api && \
mvn package -Dmaven.test.skip=true && \
nohup java -Dserver.port=8001 -jar target/api-0.0.1-SNAPSHOT.jar&
#文件服务器部署
cd /root/xiafei/file-server &&\
mvn package -Dmaven.test.skip=true && \
nohup java -Daccess.key.secret="" -Daccess.key.id="" -Dserver.port=8002 -jar target/file-0.0.1-SNAPSHOT.jar &
#聊天服务器部署
cd /root/xiafei/web-chat-room && \
mvn package -Dmaven.test.skip=true && \
nohup java -Dserver.port=8003 -jar target/cloud-0.0.1-SNAPSHOT.jar&


#部署个人主页
rm -rf /usr/share/nginx/xia-fei.github.io && \ 
cp -r /root/xiafei/xia-fei.github.io /usr/share/nginx/xia-fei.github.io

#前端项目部署
#部署sql分析前端
cd /root/xiafei/toolbox-web/sql-resolve && \ 
npm run build && \
rm -rf /usr/share/nginx/sql-resolve && \
cp -rf /root/xiafei/toolbox-web/sql-resolve/dist /usr/share/nginx/sql-resolve

#部署notebook
cd /root/xiafei/toolbox-web/notebook && \
npm run build && \
rm -rf /usr/share/nginx/notebook && \
mv /root/xiafei/toolbox-web/notebook/dist /usr/share/nginx/notebook
