package com.ozalp.api_gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RedisAuthFilter extends AbstractGatewayFilterFactory<RedisAuthFilter.Config> {

    private final ReactiveStringRedisTemplate redisTemplate;

    public RedisAuthFilter(ReactiveStringRedisTemplate redisTemplate) {
        super(Config.class);
        this.redisTemplate = redisTemplate;
    }

    public static class Config {}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Token bulunamadı", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            // Redis'te bu token var mı kontrol et (Örn: "auth:token_string" key'i ile sakladığını varsayıyoruz)
            return redisTemplate.hasKey("auth:" + token)
                    .flatMap(exists -> {
                        if (Boolean.TRUE.equals(exists)) {
                            // Token geçerli, devam et
                            return chain.filter(exchange);
                        } else {
                            return onError(exchange, "Geçersiz veya süresi dolmuş token", HttpStatus.UNAUTHORIZED);
                        }
                    });
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("{\"status\": %d, \"error\": \"%s\"}", status.value(), err);
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes());
        return response.writeWith(Mono.just(buffer));
    }
}