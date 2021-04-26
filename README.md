#homework-docker
###How to start
`sh run.sh`
###Basic
1. 至少自己 build 2 个 docker images (Dockerfile)
- [service Dockerfile](Dockerfile)
- [nginx Dockerfile](nginx/Dockerfile)
2. 使用 docker-compose 管理 docker container
    见[docker-copose.yml](docker-compose.yml)
3. 画出架构图
[架构图](diagram.jpg)
4. 描述Docker的网络模式以及区别，使用场景？
 
 |  网络模式   | 特点  | 使用场景  |
 |  ----  | ----  | ----  |
 | bridge  | 默认网络，Docker启动后创建docker0网桥 | 可以创建自定义网络 |
 | host  | 容器没有独立的network namespace，而是与宿主机共用 | 希望使用宿主机网络 |
 | none  | 有独立的network space,但不为容器进行网络配置 | 手动配置，对接公司的IPAM |
 | container  | 与指定容器使用同一个network space | k8s默认使用 |
 
###Advanced
1. 简要描述AUFS文件系统的特点以及优缺点，如何解决性能问题，如何实现数据持久化（如数据库重启后数据不丢失），并在作业中体现
- 特点：AUFS能将一台机器上的多个目录或文件，以联合的方式提供统一视图进行管理。AUFS是docker最早支持的storage driver。
- 优点：支持COW(copy-on-write)，存储和内存的使用效率较高，使用这种方式容器启动速度较快
- 缺点：docker的layer较深时效率较为低下
- 性能问题：容器里面存储的COW机制和特殊的FS类型决定了IO性能的低下，而且容量也受限制。如果使用aufs，多个容器并发情况会产生io瓶颈，更坏的是数据会和容器同生共死，这也违背了计算和数据分离的原则，为容器的迁移或者故障恢复制造麻烦。采用-v参数往容器里面挂外部卷的方式可以分离数据和服务，此外，-v可以挂多个物理磁盘或者外部存储，也解决了io瓶颈的问题。\
- 作业中体现：见[docker-copose.yml](docker-compose.yml)使用volumes挂载redis数据
2. 至少使用4个docker containers并分布在不同network，要求可以实现：nginx容器可以外部访问，nginx可以访问后端服务，后端服务可以访问数据库，nginx不能访问数据库
3. 简要描述Docker和虚拟机的对比
- 传统虚拟机是虚拟出一套硬件后，在其运行一个完成的操作系统，在系统上再运行所需的应用进程。
- 容器内的应用进程直接运行于宿主的内核，容器没有自己的内核，而且也没有进行硬件的虚拟。因此，容器要比传统的虚拟机更为轻便。

| 特性 | 容器 | 虚拟机 |
|  ----  | ----  | ----  |
| 启动 | 秒级 | 分钟机 |
| 硬盘使用 | 一般MB | 一般GB |
| 性能 | 接近原生 | 较弱 |
| 系统支持 | 单机支持上千个容器 | 几十个 |
4. 将自己的docker image放在Docker hub / AWS ECR，image一定不要包含敏感信息，注意private和public权限 

5. 思考：容器如何实现高可用（optional）
- 通过k8s实现容器集群管理和高可用，部署多个service实例并实现负载均衡
