# SpringCloudDemo
1. @Selector对应的路径: /actuator/discoveredServices/serviceId?serviceId=CLOUD-EUREKA-SERVER
2. @ConditionalOnBean(DiscoveryClient.class)导致自动配置失败, 原因是: 该bean实例化时机晚于当前@Bean注入时机,去掉即可