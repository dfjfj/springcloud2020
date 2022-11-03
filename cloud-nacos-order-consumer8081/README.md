一.
本工程主要测试使用sentinel作为微服务中的服务治理组件, sentinel以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

二.
本工程还测试使用了seata实现分布式事务.要点如下:
①: 要使用seata, 必须在全局事务发起方(标注有@GlobalTransactional的方法所在的服务)的pom中引入seata相关依赖. 其他关联服务无需引入!!!
②: seata-server端的配置: 
    注册中心使用nacos, 配置中心使用file即可. 在file.conf中修改store.type配置
    启动命令: seata-server.bat -h 127.0.0.1 -p 8091 -m db -n 1
    tip: 端口默认8091, 由于client端中netty默认注册RM时使用的就是8091, 且暂时未找到修改默认配置的地方,故暂时使用该默认端口.
    数据库中新增3张表.
    sql文件可以在https://github.com/seata/seata/blob/1.3.0/script/server/db/mysql.sql中找到
③: seata-client端的配置:
    registry.conf中配置的服务名称必须和server端一致.(吐槽下, seata配置在spring-cloud中真不简单, 不能完全容入boot中. nacos就挺不错的)
    在主启动类上添加@EnableAutoDataSourceProxy 开启seata数据源自动代理
    在需要使用全局事务的方法上增加@GlobalTransactional注解
    在全局事务所关联的所有微服务的各自数据库中新增一张表,undo_log
    建表sql(mysql类型):
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
--- 说明:
默认使用seata中的AT模式, 无代码侵入性. 
一阶段：业务数据和回滚日志记录在同一个本地事务中提交，释放本地锁和连接资源。
二阶段：
提交异步化，非常快速地完成。
回滚通过一阶段的回滚日志进行反向补偿。

注意:
    在数据库本地事务隔离级别:读已提交(Read Committed)或以上的基础上, Seata(AT模式)的默认全局隔离级别是: 读未提交(Read Uncommitted).
 
三.
本工程还测试了使用雪花算法,实现数据库分布式主键id
要点:
    hutool工具包中自带雪花算法的实现工具类. 该工具类生成的Snowflake对象是单例模式. 注意使用时的方式.
    雪花算法中的默认时间起始值是1970年一月1日0时, 因此默认使用期限是2039年.(由前14位二进制值得出该结论)

备注: 
    本工程为测试demo, service层直接调用mapper, 生产代码必须调用repository层.
       
    

    