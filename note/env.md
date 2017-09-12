## 环境安装
#### centos6.8
#### 1. 更改系统源配置
阿里云 源配置官网 http://mirrors.aliyun.com
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