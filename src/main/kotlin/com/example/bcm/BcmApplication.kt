package com.example.bcm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
class BcmApplication

fun main(args: Array<String>) {
    runApplication<BcmApplication>(*args)
}
