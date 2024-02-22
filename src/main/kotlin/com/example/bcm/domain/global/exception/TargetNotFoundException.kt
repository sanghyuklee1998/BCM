package com.example.bcm.domain.global.exception

data class TargetNotFoundException(
        override val message: String? = "target post is not found"
) : RuntimeException()