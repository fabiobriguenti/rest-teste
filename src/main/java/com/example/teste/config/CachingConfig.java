package com.example.teste.config;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;

import javax.cache.CacheManager;
import javax.cache.Caching;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {
		
	@Bean
    JCacheCacheManager jCacheCacheManager() {
        return new JCacheCacheManager(cacheManager());
    }
	
	@Bean
    CacheManager cacheManager() {
        CacheConfiguration<Integer, LocalDate> cacheClientesConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(
        		Integer.class,
        		LocalDate.class,
                ResourcePoolsBuilder.heap(5000))
        		.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(1)))
        		.build();
        
        Map<String, CacheConfiguration<?, ?>> caches = new ConcurrentHashMap<String, CacheConfiguration<?,?>>();
        caches.put("cacheClientesPorCPF", cacheClientesConfig);
        caches.put("cacheClientesPorApolice", cacheClientesConfig);
        caches.put("cacheClientesPorPlaca", cacheClientesConfig);
        caches.put("cacheClientesPorChassi", cacheClientesConfig);
        
        EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider();
        
        return provider.getCacheManager(provider.getDefaultURI(), new DefaultConfiguration(caches, provider.getDefaultClassLoader()));
    }
}