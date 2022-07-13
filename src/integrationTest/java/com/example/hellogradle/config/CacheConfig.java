package com.example.hellogradle.config;

import com.hazelcast.com.google.common.cache.CacheBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@TestConfiguration
public class CacheConfig {
    @Bean
    public Config hazelCastConfig() {
        return null;
    }

    @Bean
    public HazelcastInstance hazelCastInstance(Config hazelCastConfig){
        return null;
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .maximumSize(10)
                        .build().asMap(), false);
            }
        };
    }

}
