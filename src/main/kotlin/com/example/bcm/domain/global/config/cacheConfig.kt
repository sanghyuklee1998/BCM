package com.example.bcm.domain.global.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CachingConfig {
    @Bean
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager()
    }


    // spring-starter-cache 사용 시, 기본으로 등록되는 캐시매니저 = ConcurrentMapCacheManager
    // 또 유명한건, EhCacheManager, CaffeineCacheManager
    // CaffeineCacheManager -> 벤치마킹 성능이 가장 좋다. 삭제에도 개빠른 속도를 보여서 용이함

}
