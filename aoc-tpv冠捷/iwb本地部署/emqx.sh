#!/bin/bash

function installemqx(){
  # 检查是否安装了emqx
  if [ ! -d "/usr/local/emqx" ]; then
    echo "emqx is not installed. Installing now..."
    
    sudo unzip emqx-4.4.19-otp24.3.4.2-1-ubuntu22.04-amd64.zip
	sudo mv emqx/ /usr/local
	
	sudo sh -c 'echo "[Unit]
Description=EMQ X Broker
After=network.target
 
[Service]
Type=forking
User=root
Group=root
ExecStart=/usr/local/emqx/bin/emqx start
ExecStop=/usr/local/emqx/bin/emqx stop
ExecReload=/usr/local/emqx/bin/emqx restart
Restart=on-failure
LimitNOFILE=1048576
 
[Install]
WantedBy=multi-user.target" > /etc/systemd/system/emqx.service'

    # 重载systemd以读取新的服务文件
    sudo systemctl daemon-reload
     
    # 启动emqx服务
    sudo systemctl start emqx
     
    # 设置emqx服务开机自启
    sudo systemctl enable emqx
	
    # 检查安装是否成功
    if [ -d "/usr/local/emqx" ]; then
  	  echo "emqx installation successful."
    else
  	  echo "emqx installation failed."
  	  exit 1
    fi
  else
    echo "emqx is already installed."
  fi
}

installemqx