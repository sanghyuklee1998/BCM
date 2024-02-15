package com.example.bcm.domain.member.dto

import com.example.bcm.domain.member.model.Member

data class CreateMemberRequest(
    val email: String,
    val nickname: String
){
    fun to(): Member{
        return Member(
            email = email,
            nickname = nickname
        )
    }
}