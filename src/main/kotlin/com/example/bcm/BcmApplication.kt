package com.example.bcm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BcmApplication

fun main(args: Array<String>) {
    runApplication<BcmApplication>(*args)
}
