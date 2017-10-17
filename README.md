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
    - 使用EurekaClient或者DiscoveryClient通过服务名获取服务信息
    - zone，保障注册中心的高可用性。
- Hystrix 断路器
    - 如果某一个服务单元发生故障，通过断路器的故障监控，向调用方返回一个错误而不是长时间的wait，达到快速释放资源的目的，从而避免引发其他故障。
    - 提供了一个 Hystrix Dashboard监控HystrixCommond的信息
- Zuul 路由和过滤器
- Ribbon 负载均衡
    - Rule 选出server的规则
        - RoundRobinRule 循环选择
        - AvailabilityFilteringRule  先过滤清单，再轮询。默认情况下有三次没练上就认为是断开了，然后等待三十秒认为是正常的了。又断开后等待指数性增加的一个时间后恢复。
        - WeightedResponseTimeRule
    - Ping 保证server在线
    - ServerList 服务列表。可以是动态的也可以是静态的，动态的则每隔一段时间会刷新该列表。
        - DiscoveryEnabledNIWSServerList 通过EurekaClient更新服务列表
    - ServerListFilter 过滤EurekaClient发现的服务。
### Spring Cloud Bus
    事件总线
### Spring Cloud Stream
    构建事件驱动的微服务应用程序的框架。
    使用kafka或者RabbitMQ在spring boot应用之间进行消息传递。