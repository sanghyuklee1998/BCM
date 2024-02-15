package com.example.bcm.domain.member.dto

data class CreateMemberRequest(
    val email: String,
    val nickname: String
)