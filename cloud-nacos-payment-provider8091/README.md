本工程主要测试使用nacos的基本使用: 注册中心和配置中心
nacos单机模式下的启动命令:
    startup.sh -m standalone

备注:
    本工程为测试demo, service层直接调用mapper, 生产代码必须调用repository层.