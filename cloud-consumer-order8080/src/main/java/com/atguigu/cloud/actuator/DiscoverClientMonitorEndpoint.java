package com.atguigu.cloud.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@Endpoint(id = "discoveredServices")
public class DiscoverClientMonitorEndpoint {

    @Autowired
    private DiscoveryClient discoveryClient;

    @ReadOperation
    public List<String> getAllServiceIds() {
        return discoveryClient.getServices();
    }


    @ReadOperation
    public List<ServiceInstance> getInstances(@Selector String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }
}
