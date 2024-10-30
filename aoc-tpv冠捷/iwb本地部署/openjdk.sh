#!/bin/bash

function installjdk(){
  # 检查是否安装了OpenJDK
  if ! [ -d "/opt/java/jdk-11" ]; then
    echo "OpenJDK is not installed. Installing now..."
    
    sudo mkdir /opt/java
    sudo mv openjdk-11+28_linux-x64_bin.tar.gz /opt/java && cd /opt/java
    sudo tar zxvf openjdk-11+28_linux-x64_bin.tar.gz
    sudo sh -c 'echo "export JAVA_HOME=/opt/java/jdk-11" >> /etc/profile'
    sudo sh -c 'echo "export PATH=$PATH:$JAVA_HOME/bin" >> /etc/profile'
    source /etc/profile
    
    # 检查安装是否成功
    if [ -d "/opt/java/jdk-11" ]; then
  	  echo "OpenJDK installation successful."
    else
  	  echo "OpenJDK installation failed."
  	  exit 1
    fi
  else
    echo "OpenJDK is already installed."
  fi
}

installjdk