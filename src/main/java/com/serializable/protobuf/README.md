#安装profobuf   
#####[参考链接](https://www.jianshu.com/p/cae40f8faf1e)

###1、windows 安装
protoc 下载：[官方下载地址](https://github.com/protocolbuffers/protobuf/releases/tag/v3.7.1)，然后将 bin 路径添加到 path 环境变量下去
查看是否安装成功：控制台输入 protoc --version ，控制台输出版本信息代表成功，如： libprotoc 3.7.1

---
###2、idea 安装插件
ideal 插件库搜索安装 Protobuf Support 即可
此插件可以不用安装，但是这有助于一些源码阅读的便利性和一些编码提示
>IDE 最大的作用不就是快速编码嘛

---
###3、编写 proto 文件
定义一个 JetProtos.proto 文件
>查看 JetProtos.proto

---
###4、编译成 java 文件
进入 proto 文件所在路径，输入下面 protoc 命令（后面有三部分参数），然后将编译得出的 java 文件拷贝到项目中即可（此 java 文件可以理解成使用的数据对象）：
>参数说明：
>
>1. -I 等价于 -proto_path：指定 .proto 文件所在的路径
>2. --java_out：编译成 java 文件时，标明输出目标路径
>3. ./JetProtos.proto：指定需要编译的 .proto 文件
 