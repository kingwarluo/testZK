#!/bin/bash

function installnginx(){
  # 检查是否安装了nginx
  if ! [ -d "/usr/local/nginx" ]; then
    echo "nginx is not installed. Installing now..."
	
	sudo tar zxvf nginx-1.18.0.tar.gz
	sudo mv nginx-1.18.0 nginx
	sudo mv nginx/ /usr/local/nginx
    # 安装依赖包
	apt-get install libpcre3 libpcre3-dev
    apt-get install zlib1g zlib1g-dev
	./configure
	make
	sudo make install

    sudo sh -c 'echo "[Unit]
Description=nginx Service
After=network.target

[Service]
User=root
Group=root
ExecStart=/usr/local/nginx/sbin/nginx
ExecStop=/usr/local/nginx/sbin/nginx -s stop
Restart=on-failure
Type=forking

[Install]
WantedBy=multi-user.target" > /etc/systemd/system/nginx.service'
    
	# 重载systemd以读取新的服务文件
    sudo systemctl daemon-reload
     
    # 启动nginx服务
    sudo systemctl start nginx
     
    # 设置nginx服务开机自启
    sudo systemctl enable nginx
	
    # 检查安装是否成功
    if [ -d "/usr/local/nginx" ]; then
  	  echo "nginx installation successful."
    else
  	  echo "nginx installation failed."
  	  exit 1
    fi
  else
    echo "nginx is already installed."
  fi
}

installnginx