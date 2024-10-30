#!/bin/bash

function installnacos(){
  # 检查是否安装了nacos
  if [ ! -d "/usr/local/nacos" ]; then
    echo "nacos is not installed. Installing now..."
    
	tar -zxvf nacos-server-2.3.2.tar.gz
	sudo mv nacos/ /usr/local/nacos
	# 创建软连接，将jdk目录软链接到/usr/java
	ln -s /opt/java/jdk-11 /usr/java
	# 添加nacos到系统服务
	sudo sh -c 'echo "[Unit]
Description=Nacos Server
After=network.target
 
[Service]
User=root
Group=root
Type=forking
ExecStart=/usr/local/nacos/bin/startup.sh -m standalone
ExecStop=/usr/local/nacos/bin/shutdown.sh
Restart=on-failure
 
[Install]
WantedBy=multi-user.target" > /etc/systemd/system/nacos.service'

	# 重载systemd以读取新的服务文件
    sudo systemctl daemon-reload
     
    # 启动nacos服务
    sudo systemctl start nacos
     
    # 设置nacos服务开机自启
    sudo systemctl enable nacos
    
    # 检查安装是否成功
    if [ -d "/usr/local/nacos" ]; then
  	  echo "nacos installation successful."
    else
  	  echo "nacos installation failed."
  	  exit 1
    fi
  else
    echo "nacos is already installed."
  fi
}

installnacos