## 环境安装
#### centos6.8
#### 1. 更改系统源配置
阿里云 源配置[官网] (http://mirrors.aliyun.com)
* 源配置步骤

  1. 备份
  ```
  sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
  ```
  2. 下载CentOS-Base.repo到/etc/yum.repos.d
  ```
  sudo wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-6.repo
  ```
  3. 运行yum makecache生成缓存
  ```
  yum makecache
  ```

#### 2. jdk的下载与安装
* jdk安装
  1. 清理系统默认自带的jdk
  ```
  rpm -qa | grep jdk 查找系统默认jdk
  sudo yum remove xxx (xxx为上一个命令查询到的结果) 卸载查询到的jdk
  ```
  2. 赋予权限
  ```
  sudo chmod 777 jdk-7u80-linux-i586.rpm
  ```
  3. 安装
  ```
  sudo rpm -ivh jdk-7u80-linux-i586.rpm
  ```
  4. 默认安装路径 /usr/java
  ```
  /usr/java/jdk1.7.0.80
  ```
  5. jdk配置环境变量
    * 5.1 sudo vim /etc/profile
    * 5.2 在最下方添加java环境变量
      ```
      export JAVA_HOME=/usr/java/jdk1.7.0.80
      export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
      注：JAVA_HOME为您安装jdk的路径
      ```
    * 5.3 在export PATH中添加$JAVA_HOME/bin
      ```
      export PATH=$JAVA_HOME/bin:$PATH
      ```
    * 5.4 保存退出,通过vim的 ":wq"命令进行保存退出
    * 5.5 使配置生效 source /etc/profile 
    * 5.6 jdk验证 java -version

#### 3. tomcat的下载与安装
* tomcat简介
Tomcat是一个WEB容器，JavaEE程序可以在此运行(Servlet解析器)
* tomcat安装
  * 下载
  * 解压缩
    sudo tar -zxvf apache-tomcat-7.0.73.tar.gz
  * 配置环境变量
    sudo vim /etc/profile
    在最下方增加
    ```
    export CATALINA_HOME=/developer/tomcat
    CATALINA_HOME为tomcat安装路径
    ```
    ":wq" 保存退出
    使配置生效 source /etc/profile
    配置UTF-8字符集
    (1) 进入tomcat安装目录的conf文件夹, 编写server.xml
    (2)找到默认8080端口位置在xml节点末尾增加
    URIEncoding="UTF-8"
* tomcat验证
  (1)进入系统解压缩后的tomcat目录
  (2)进入bin目录
  (3)执行./startup.sh
  (4)打开浏览器,访问http://localhost:8080端口
  (5)宿主机无法访问虚拟机，虚拟机防火墙配置问题[地址](http://blog.csdn.net/xiaoxinghehe/article/details/8248984)
* tomcat常用命令
  1. 启动 startup.sh
  2. 关闭 shutdown.sh

#### 4. Maven概览
  * Maven简介
    * 是什么? 
  Apache Maven Project - Apache的优秀开源项目
  Maven是Java项目的构建和管理工具
    * 能做什么
    (1) 用Maven可以方便的创建项目，基于archetype可以创建多种类型的java项目
    (2) Maven仓库对jar包(artifact)进行统一管理，
    避免jar文件的重复拷贝和版本冲突
    (3) 团队开发,Maven管理项目的RELEASE和SNAPSHOT版本，方便多模块(Module)项目的各个模块之间的快速集成
  * Maven安装
   * 下载3.0.5
   * 解压缩
   tar -zxvf apache-maven-3.0.5-bin.tar.gz
   配置环境变量
   (1) sudo vim /etc/profile
   (2) export MAVEN_HOME=/developer/maven
   (3) export PATH=$PATH:$JAVA_HOME/bin:$MAVEN_HOME/bin
   (4) 执行source /etc/profile 使配置生效
   (5) 安装认证 mvn -version
   (6) Error: JAVA_HOME is not defined correctly [解决方案](http://blog.sina.com.cn/s/blog_49cd89710102uyr5.html)
  * Maven常用命令
    * 清除命令: mvn clean
    * 编译命令: mvn compile
    * 打包命令: mvn package
    * 跳过单元测试 mvn clean package -Dmaven.test.skip=true
  * Maven常用配置
    settings.xml 文件配置
    配置远程仓库地址，个人账号密码等等

#### 5. vsftpd概览
* vsftpd简介
  * vsftpd是"very secure FTP daemon"的缩写,
    是一个完全免费的，开源的ftp服务器软件
  * vsftpd是一款在Linux发行版中最受推崇的FTP服务器程序
    小巧轻快，安全易用，支持虚拟用户，支持带宽限制等功能
* vsftpd安装
  * Linux 安装，创建虚拟用户，配置，防火墙
    rpm -qa | grep vsftpd可通过这个检查是否已经安装vsftpd
    (1) 执行sudo yum -y install vsftpd
    (2) 是否使用sudo权限执行请根据你的具体环境来决定
    (3) 默认配置文件在 /etc/vsftpd/vsftpd.conf
  * 创建虚拟用户
    (1) 选择在根或者用户目录下创建ftp文件夹: mkdir ftpfile, 如: /ftpfile
    (2) 添加匿名用户: useradd ftpuser -d /ftpfile -s /sbin/nologin
    (3) 修改ftpfile权限: chown -R ftpuser.ftpuser /ftpfile
    (4) 重设ftpuser密码: passwd ftpuser (123456)
    是否使用sudo权限执行请根据你的具体环境来决定
  * 配置
  (1) cd /etc/vsftpd
  (2) sudo vim chroot_list
  (3) 把刚才新增的虚拟用户添加到此配置文件中，后续要引用
  (4) :wq 保存退出, 查看是否添加成功 sudo cat chroot_list
  (5) sudo vim /etc/selinux/config 修改为SELINUX=disabled
  (6) :wq 保存退出
  注意：如果验证的时候碰到550拒绝访问，请执行:
  sudo setsebool -P ftp_home_dir 1
  然后重启linux服务器,执行reboot命令
  (7) sudo vim /etc/vsftpd/vsftpd.conf
  (8) 添加或更新配置
    防火墙配置
    sudo vim /etc/sysconfig/iptables
    ...
    将以上配置添加到防火墙配置中
    :wq 保存退出
    ```
    #vsftpd 
    -A INPUT -p TCP --dport 61001:62000 -j ACCEPT 
    -A OUTPUT -p TCP --sport 61001:62000 -j ACCEPT 
    -A INPUT -p TCP --dport 20 -j ACCEPT 
    -A OUTPUT -p TCP --sport 20 -j ACCEPT 
    -A INPUT -p TCP --dport 21 -j ACCEPT 
    -A OUTPUT -p TCP --sport 21 -j ACCEPT
    ```
    sudo service iptables restart 执行命令重启防火墙

* vsftpd验证
  (1) 执行sudo service vsftpd restart
  注: 第一次启动时Shutting down vsftpd是failed不用理会，
  因为这是重启命令，保证Starting vsftpd是OK即代表vsftpd服务成功
  (2) 打开浏览器访问: ftp:// 10.211.55.6
  (4) 输入用户名和密码
* vsftpd常用命令
  启动: sudo service vsftpd start
  关闭: sudo service vsftpd stop
  重启: sudo service vsftpd restart

#### 5. nginx概览
  * Nginx简介
  Nginx是一款轻量级Web服务器，也是一款反向代理服务器
  Nginx能干的事情很多，这里简要罗列一些
    * 可直接支持Rails和PHP的程序
    * 可作为HTTP反向代理服务器
    * 作为负载均衡服务器
    * 作为邮件代理服务器
    * 帮助实现前端动静分离
  特点:
    * 高稳定
    * 高性能
    * 资源占用少
    * 功能丰富
    * 模块化结构
    * 支持热部署
  * Nginx安装
  1. 安装gcc(命令: yum install gcc)
  备注: 可以输入gcc -v 查询版本信息，看系统是否自带安装
  2. 安装pcre(命令: yum install pcre-devel)
  3. 安装zlib(命令: yum install zlib zlib-devel)
  4. 安装openssl (命令: yum install openssl openssl-devel)
  备注：如需支持ssl，才需安装openssl
  综合命令: yum -y install gcc zlib zlib-devel pcre-devel openssl
  openssl-devel
  5. 下载源码包，选择稳定版本，解压缩安装[地址](https://nginx.org/) 
    * wget https://nginx.org/download/nginx-1.12.1.tar.gz
    * tar -zxvf nginx-1.10.2.tar.gz
  6. 安装
    * 进入nginx目录之后之行./configure (也可以指定安装目录，增加参数 --prefix=/usr/nginx 如果不指定路径，可以通过whereis nginx进行查询 默认安装在/usr/local/nginx)
    * 继续执行 make
    * 继续执行 make install
    * 进入安装目录cd /usr/local/nginx/sbin
    * 执行启动 sudo ./nginx
    * ps aux | grep nginx 查看nginx是否启动
    * 访问localhost:80 验证
  * Nginx常用命令
    * 测试配置文件
      安装路径下的 /nginx/sbin/nginx -t
    * 启动命令
      安装路径下的 /nginx/sbin/nginx
    * 停止命令
      安装路径下的 /nginx/sbin/nginx -s stop
      或者: nginx -s quit
    * 重启命令
      安装路径下的 /nginx/sbin/nginx -s reload
    * 查看进程命令
      ps -ef | grep nginx
    * 平滑重启
      kill -HUP [Nginx主进程号(即查看进程命令查到的PID)]
    * 增加防火墙访问权限
      * sudo vim /etc/sysconfig/iptables
      * -A INPUT -p tcp -m state --state NEW
      -m tcp --deport 80 -j ACCEPT
      * 保存退出
      * 重启防火墙(sudo service iptables restart)        
  * Nginx项目配置及验证
    Nginx 虚拟域名配置及测试验证
    1. 编辑 sudo vim /usr/local/nginx/conf/nginx.conf
    增加 include vhost/*.conf
    保存退出
    2. 在/usr/local/nginx/conf 目录新建vhost文件夹
    即: /usr/local/nginx/conf/vhost
    3. 创建域名转发配置文件
    4. 启动(重启)验证
      * 启动: ${nginx}/sbin/nginx
      * 重启: ${nginx}/sbin/nginx -s reload
      注: ${nginx}代表安装在系统中的路径，例如/usr/local/nginx
    5. 访问验证
    使用默认80端口访问验证
    http://localhost:80
    或
    http://127.0.0.1:80
    6. 指向端口
        learning.yhyecho.com.conf
        yhyecho.com.conf(既指向端口又指向目录)
       指向目录
        img.yhyecho.com.conf
        s.yhyecho.com.conf
  * Nginx本地玩耍注意事项
  可以配置域名转发，但是请一定要配置host，并且使host生效之后才可以，设置完成之后要重启浏览器
  1. sudo vim /etc/hosts
  2. 添加好对应域名及ip
  3. :wq保存退出

#### 6. mysql概览
* Mysql简介
Mysql是一个关系型数据库管理系统，由瑞典Mysql AB公司开发，目前属于Oracle旗下产品
特点: Mysql所使用的SQL语言是用于访问数据库的最常用标准化语言，Mysql软件采用双授权政策，分为社区版和商业版，由于其体积小，速度快，总体拥有成本低，尤其是开放源码这一点，一般中小型网站的开发都会选择MySQL作为网站数据库
* Mysql安装
  * 执行yum -y install mysql-server
  注：是否使用sudo权限执行，请根据你具体环境来决定
  * rpm -qa | grep mysql-server (检查是否已经安装mysql－server)
  * 默认配置文件在 /etc/my.cnf
* Mysql服务启动
  * 启动mysqld服务service mysqld start或
  /etc/rc.d/init.d/mysqld start
  * Mysql初始化环境设置
  因为还未设置密码，执行mysql -u root登录Mysql服务器
* Mysql配置
  * vim /etc/my.cnf
  * 添加配置，在[mysqld]节点下添加
    default-character-set=utf8
    character-set-server=utf8
  * :wq 保存退出
* 自启动配置
  * 执行chkconfig mysqld on
  * 执行chkconfig --list mysqld查看(如果2-5位启用on状态即OK)
* 防火墙配置
  * sudo vim /etc/sysconfig/iptables
  * -A INPUT -p tcp -m tcp --dport 3306 -j ACCEPT (将以上配置添加到防火墙配置中)      
  * :wq 保存退出
  * sudo service iptables restart 执行命令重启防火墙
* 查看用户修改密码
  * 查看目前mysql用户
  ```
  select user,host,password from mysql.user
  ```  
  * 修改root密码
  set password for root@localhost=password('root');
  set password for root@127.0.0.1=password
  ('root')
  * exit退出mysql
  * 重新登录mysql输入mysql -u root -p
  * 输入密码登录成功
* 删除匿名用户,执行以下sql
查看是否有匿名用户: select user,host from mysql.user;
删除匿名用户: delete from mysql.user where user='';
再次查看: select user,host from mysql.user;
刷新，使以上操作生效: flush privileges;
* 插入mysql新用户
```
insert into mysql.user(Host,User,Password) values
("localhost", "yourusername", password("yourpassword"));
```
* 使操作生效 flush privileges;
* 创建新的database
```
CREATE DATABASE `mmall`
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;  
```
* 查看数据库的权限分配情况
```
select * from mysql.user \G
\G 表示格式化输出
```
* 本地用户赋予所有权限
```
grant all privileges on mmall.* to yourusername@localhost identified by 'yourpassword'
with grant option;
```
* 给账号开通外网所有权限
```
grant all privileges on mmall.* to 'yourusername'@'%' identified by 'yourpassword'
with grant option;
```
注：根据自己实际情况决定是否开什么权限
```
grant select,insert,update on mmall.* to
yourusername@'192.168.11.11' identified by 'yourpassword' with grant option;
代表只开通增改查给指定的账号，并也指定ip地址
```
* 刷新权限(flush privileges;)
* Mysql验证
  查看ip地址: ifconfig
* Mysql常用命令
1. 启动: sudo service mysqld start
2. 关闭: sudo service mysqld stop
3. 重启: sudo service mysqld restart
* mysql登录(mysql -u ${yourusername} -p)
* SQL简介
dql -select
dml -insert update delete
ddl -create table create view
dcl -grant

#### 7. git概览
* git 简介
  Git是一款免费，开源的分布式版本控制系统
  特点:
  Git是一个开源的分布式版本控制系统，可以有效，高速
  的处理从很小到非常大的项目版本管理
* git 安装
  1. 从github官网[下载](https://github.com/git/git/releases)
  ```
  sudo wget https://github.com/git/git/archive/v2.8.6.tar.gz
  (是否使用sudo权限执行，根据你的具体环境来决定)
  ```
  2. 安装git所需依赖
  ```
  sudo yum -y install zlib-devel openssl-devel
  cpio expat-devel gettext-devel curl-devel perl-ExtUtils-CBuilder perl-ExtUtils-MakeMaker
  (是否使用sudo权限执行，根据你的具体环境来决定)
  ```
  3. 解压, 编译安装
    1. sudo tar -zxvf v2.8.6.tar.gz
    2. cd git-2.8.6
    3. sudo make prefix=/usr/local all
    4. sudo make prefix=/usr/local install
  4. 安装验证
  git --version
* git 基础配置
  * 配置用户名(提交时会引用)
  git config --global user.name "echo"
   -- 请把echo替换成自己的用户名
  * 配置邮箱(提交时会引用)
  git config --global user.email "yhyecho@gmail.com"
   -- 请把yhyecho@gmail.com替换成自己的用户邮箱
  * 其他配置
    git config --global merge.tool "kdiff3"
    -- 要是没装KDiff3就不用配置这一行
    git config --global core.autocrlf false
    -- 让Git不要管Windows/Unix换行符转换的问题
  * 编码配置
    * git config --global gui.encoding utf8
    -- 避免git gui 中的中文乱码
    * git config --global core.quotepath off
    -- 避免git status 显示的中文文件名乱码
    * Windows上还需要配置:
    git config --global core.ignorecase false
  * git ssh key pair 配置
    1. 在Linux的命令行下, 或Windows上Git Bash命令行中输入: ssh-keygen -t rsa -C "yhyecho@gmail.com"    
    2. 然后一路回车，不要输入任何密码之类的, 生成ssh key pair
    3. ssh-add ~/.ssh/id_rsa
    4. cat ~/.ssh/id_rsa.pub
    5. 注意：执行ssh-add命令时如果出现
    Could not open a connection to your authentication agent 错误
    解决方法: 
      1. 先执行 eval `ssh-agent`(是～键上的那个｀)
      2. 再执行ssh-add ~/.ssh/id_rsa成功
      3. ssh-add -l 就有新加的rsa了
* git 验证
  执行 git --version命令，出现版本信息，安装成功
* git 常用命令
  * git checkout 分支名 (切换分支)
  * git pull (拉取)
  * git push (提交)








