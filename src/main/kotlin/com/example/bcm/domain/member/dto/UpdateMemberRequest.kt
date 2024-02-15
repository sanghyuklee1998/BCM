package com.example.bcm.domain.member.dto

data class UpdateMemberRequest (
    val id: Long?,
    val email: String,
    val nickname: String,
)

