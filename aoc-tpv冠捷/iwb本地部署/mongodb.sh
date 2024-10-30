#!/bin/bash

function installmongodb(){
  # 检查是否安装了mongodb
  if [ ! -d "/usr/local/mongodb" ]; then
    echo "mongodb is not installed. Installing now..."
    
	# 解压缩
    tar -zxvf mongodb-linux-x86_64-ubuntu2204-6.0.19.tgz
     
    # 将MongoDB移动到/usr/local/mongodb目录（可选，为了方便管理）
	sudo mkdir -p /usr/local/mongodb
    sudo mv mongodb-linux-x86_64-ubuntu2204-6.0.19/ /usr/local/mongodb
     
    # 创建数据目录和日志文件
	sudo mkdir -p /usr/local/mongodb/data/db
    sudo mkdir -p /var/lib/mongo
    sudo mkdir -p /var/log/mongodb
     
	#添加mongod.conf配置
	sudo sh -c 'echo "storage:
  dbPath: /usr/local/mongodb/data/db
  journal:
    enabled: true
systemLog:
  destination: file
  logAppend: true
  path: /var/log/mongodb/mongod.log
net:
  port: 27017
  bindIp: 0.0.0.0
processManagement:
  fork: false" > /etc/mongod.conf'
	 
    # 添加MongoDB到系统服务
    sudo sh -c 'echo "[Unit]
Description=MongoDB database server
Documentation=https://www.mongodb.org/
After=network.target
 
[Service]
User=root
Group=root
Environment=DBPATH=/var/lib/mongo
Environment=LOGPATH=/var/log/mongodb/mongod.log
Environment=PIDFILEPATH=/var/run/mongodb/mongod.pid
ExecStart=/usr/local/mongodb/bin/mongod --quiet --config /etc/mongod.conf
ExecStop=/usr/local/mongodb/bin/mongod --quiet --shutdown --config /etc/mongod.conf
PIDFile=/var/run/mongodb/mongod.pid
Restart=always
 
[Install]
WantedBy=multi-user.target" > /etc/systemd/system/mongod.service'
     
    # 重载systemd以读取新的服务文件
    sudo systemctl daemon-reload
     
    # 启动MongoDB服务
    sudo systemctl start mongod
     
    # 设置MongoDB服务开机自启
    sudo systemctl enable mongod
	
    # 检查安装是否成功
    if [ -d "/usr/local/mongodb" ]; then
  	  echo "mongodb installation successful."
    else
  	  echo "mongodb installation failed."
  	  exit 1
    fi
  else
    echo "mongodb is already installed."
  fi
}

installmongodb