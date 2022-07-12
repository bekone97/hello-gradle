package com.example.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CasheConfig {

    @Bean
    public Config hazelCastConfig() {
        return new Config().setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("employee")
                                .setTimeToLiveSeconds(300)
                );

    }

    @Bean
    public HazelcastInstance hazelCastInstance(Config hazelCastConfig){
        return Hazelcast.newHazelcastInstance(hazelCastConfig);
    }

    @Bean
    public CacheManager cacheManager(@Qualifier("hazelCastInstance") HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }
}