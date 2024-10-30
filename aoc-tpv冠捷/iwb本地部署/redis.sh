#!/bin/bash

function installredis(){
  # 检查是否已安装指定版本的Redis
  if [ ! -d "/usr/local/redis" ]; then
    echo "redis is not installed. Installing now..."

    tar -zxvf redis-6.2.14.tar.gz
	sudo mv redis-6.2.14 redis
    sudo mv redis/ /usr/local/redis
	
	cd /usr/local/redis
	# 替换数据存放目录
	sudo mkdir -p /usr/local/redis/data
	sudo sed -i 's/dir \.\//dir \/usr\/local\/redis\/data\//g' redis.conf
	#启用密码验证 设置密码为root
	sudo sed -i 's/# requirepass foobared/requirepass root/g' redis.conf
	
    make
	sudo make install
	
	sudo sh -c 'echo "[Unit]
Description=Redis In-Memory Data Store
After=network.target
 
[Service]
User=root
Group=root
ExecStart=/usr/local/bin/redis-server /usr/local/redis/redis.conf
ExecStop=/usr/local/bin/redis-cli shutdown
Restart=always
 
[Install]
WantedBy=multi-user.target" > /etc/systemd/system/redis.service'
	
	# 重载systemd以读取新的服务文件
    sudo systemctl daemon-reload
     
    # 启动redis服务
    sudo systemctl start redis
     
    # 设置redis服务开机自启
    sudo systemctl enable redis

    # 检查安装是否成功
    if [ -d "/usr/local/redis" ]; then
  	  echo "redis installation successful."
    else
  	  echo "redis installation failed."
  	  exit 1
    fi
  else
    echo "redis is already installed."
  fi
}

installredis