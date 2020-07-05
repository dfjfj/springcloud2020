package com.atguigu.cloud.filter.ratelimit;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.support.ipresolver.RemoteAddressResolver;
import org.springframework.cloud.gateway.support.ipresolver.XForwardedRemoteAddressResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;


public class RemoteAddressKeyResolver implements KeyResolver {

    public static final String BEAN_NAME = "remoteAddressKeyResolver";

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // ::maxTrustedIndex takes an index which correlates to the number of trusted infrastructure running in front of Spring Cloud Gateway
        // 由于网关前方的代理数量不固定, 因此取值Integer.MAX_VALUE, 得到的永远是第一个,即真正的远程主机地址
        RemoteAddressResolver resolver = XForwardedRemoteAddressResolver
                .maxTrustedIndex(Integer.MAX_VALUE);
        InetSocketAddress inetSocketAddress = resolver.resolve(exchange);
        return Mono.just(inetSocketAddress.getAddress().getHostAddress());
    }
}
