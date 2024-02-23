package com.example.bcm.domain.global.timer

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime


    @Aspect
    @Component
    class LoggingStopWatchAdvice {



        @Around("@annotation(com.example.bcm.domain.global.timer.LoggingStopWatch)")
        fun atTarget(joinPoint: ProceedingJoinPoint): Any? {
            val startAt = LocalDateTime.now()
            println("Start At : $startAt")

            val proceed = joinPoint.proceed()

            val endAt = LocalDateTime.now()

            println("End At : $startAt")
            println("Logic Duration : ${Duration.between(startAt, endAt).toMillis()}ms")

            return proceed
        }
    }

