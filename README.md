# Spring Cloud
### 提供了快速构建分布式系统的一些工具（配置管理、服务发现、断路器、智能路由、微代理、eventbus）

## 主要项目
### Spring Cloud Config
- 集中配置管理 主要分为3个角色，配置仓库、配置中心、服务。
    - 配置仓库的数据存储 主要使用这四种，Git、SVN、local、Valut，可以复合使用
    - 配置仓库数据安全 对配置仓库中的配置文件加密
    - 推送通知 当配置仓库的配置文件修改时，通知服务
### Spring Cloud Netflix
- Eureka  服务发现 主要分为2个角色，注册中心、服务。
    - 服务链接注册中心默认使用http，可以通过```eureka.instance.nonSecurePortEnabled = false,eureka.instance.securePortEnabled=true ```修改成使用https
    - 需要授权的eurekaServer
    - 设置健康检查， 注意只能配置在application配置文件中```eureka.client.healthcheck.enabled=true```
    - 元数据，默认包含hostname、ip、port、健康状态等。自定义可以添加到eureka.instance.metadataMap里
    - 使用EurekaClient或者DiscoveryClient或者FeignClient通过服务名获取服务信息
    - zone，保障注册中心的高可用性。 如何解决。
- Hystrix 断路器
    故障 什么故障，断路器如何知道故障
    网络故障。
    - 如果某一个服务单元发生故障，通过断路器的故障监控，向调用方返回一个错误而不是长时间的wait，达到快速释放资源的目的，从而避免引发其他故障。
    - 提供了一个 Hystrix Dashboard监控HystrixCommond的信息
- Zuul 路由和过滤器
    - 手动在配置文件中设置路由
    - 连接注册中心自动获取服务列表进行路由
    网关：连接数、流量。
- Ribbon 负载均衡
    - Rule 选出server的规则
        - RoundRobinRule 循环选择
        - AvailabilityFilteringRule  先过滤清单，再轮询。默认情况下有三次没练上就认为是断开了，然后等待三十秒认为是正常的了。又断开后等待指数性增加的一个时间后恢复。
        - WeightedResponseTimeRule
    ？？？连接断开是什么意思。
        
    - Ping 保证server在线
    - ServerList 服务列表。可以是动态的也可以是静态的，动态的则每隔一段时间会刷新该列表。
        - DiscoveryEnabledNIWSServerList 通过EurekaClient更新服务列表
       ？？？ 是否有主动推送的更新列表
    - ServerListFilter 过滤EurekaClient发现的服务。
        ？？？作用是什么，场景是什么。
   
### Spring Cloud Stream
     构建事件驱动的微服务应用程序的框架。
     使用kafka或者RabbitMQ在spring boot应用之间进行消息传递。
- binder
    - app和中间件之间的适配。可以通过它修改消息destinations（kafka的topic）
- Publish-Subscribe 模式。
- 消费者组
- Bindings 
    - 通过配置把app和binder绑定到一起，然后通过修改binding的配置来达到修改binder的目的
- 模型
    - 通过binder链接外部中间件。
    - 通过inputs和ouputs连接binder和外部应用进行通信。  

- 顶级接口
    - Source 拥有单个output
    - Sink   单个的input
    - Processor 俩个都有

- 扩展
    - 消息转换： json/pojo,json/org.springframework.tuple.Tuple,
    Object(Serializable)/byte[],String/byte[], Object=>text (toString)
    - 聚合 将多个应用链接到一起，直接连接其输入输出通道，避免通过代理交换信息产生额外成本。1.0版本之后有的 1.3.5

## TODO
    stream： 多个binder的处理。消费者、生产者的props。消息转换器
    spring-cloud-cluster


## version
    Dalston.SR4
     
spring cloud web 
spring-boot-starter-web http2.0
服务间http2.0 走http 不走https  
ok.http

 问题：如何去得到当前节点的工作“线程”数。场景是版本更新。
    actuator
logs 具体的功能

日志的管理。消费者段日志如何清除。
topic的一套管理信息。
丢到那一个分区，那一个节点。
三个顶级接口是什么东，都能指定什么？例子都是如何跑的。

spring-cloud-samples/config-repo 研究

linux内核最小化的操作系统，是公用的。是容器的一部分。
所以docker镜像是不需要把内核打进去的。
由于容器是需要这个linux内核，所以docker必须跑再linux上。
由于容器内存在一个linux内核，所以docker容器运行的都是linux系统。
容器内的进程是不会访问到宿主机的类库。

默认的bridge是不可以通过hostname访问，只能通过ip访问。如果想通过hostname访问就做link。（为了兼容以前版本的docker）
如果是自己创建的bridge，都是新的bridge。它有一个嵌入式dns服务器。所以可以直接使用hostname访问。

dockerFile每执行一步会commit。

nio,操作系统支持异步驱动就用异步驱动，如果没有就写正常的异步操作（假的）。
idle
