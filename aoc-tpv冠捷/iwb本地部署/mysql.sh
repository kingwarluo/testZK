#!/bin/bash

function installmysql(){
  # 检查是否已安装指定版本的mysql
  if ! dpkg -s mysql-server &> /dev/null; then
    echo "mysql is not installed. Installing now..."
    
    #安装依赖
    sudo dpkg -i libaio1_0.3.112-13build1_arm64.deb
	sudo dpkg -i libtinfo5_6.3-2ubuntu0.1_amd64.deb
	
	#设置root密码
	#开始安装mysql
    sudo dpkg -i mysql-common_5.7.29-1ubuntu18.04_amd64.deb
    #此步需要输入mysql实例的root密码
    sudo dpkg-preconfigure mysql-community-server_5.7.29-1ubuntu18.04_amd64.deb

	#安装mysql
	sudo dpkg -i libmysqlclient20_5.7.29-1ubuntu18.04_amd64.deb
    sudo dpkg -i libmysqlclient-dev_5.7.29-1ubuntu18.04_amd64.deb
    sudo dpkg -i libmysqld-dev_5.7.29-1ubuntu18.04_amd64.deb
    sudo dpkg -i mysql-community-client_5.7.29-1ubuntu18.04_amd64.deb
    sudo dpkg -i mysql-client_5.7.29-1ubuntu18.04_amd64.deb
    sudo dpkg -i mysql-common_5.7.29-1ubuntu18.04_amd64.deb

	#安装mysql服务
	#安装依赖
	sudo dpkg -i libmecab2_0.996-14build9_amd64.deb

    #安装mysql服务
    sudo dpkg -i mysql-community-server_5.7.29-1ubuntu18.04_amd64.deb
    sudo dpkg -i mysql-server_5.7.29-1ubuntu18.04_amd64.deb
	
	# 检查MySQL状态
    systemctl status mysql
 
    #设置开机启动
    systemctl enable mysql
	
    # 检查安装是否成功
    if dpkg -s mysql-server &> /dev/null; then
  	  echo "mysql installation successful."
    else
  	  echo "mysql installation failed."
  	  exit 1
    fi
  else
    echo "mysql is already installed."
  fi
}

installmysql