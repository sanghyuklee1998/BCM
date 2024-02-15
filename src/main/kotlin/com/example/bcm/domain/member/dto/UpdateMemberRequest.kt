package com.example.bcm.domain.member.dto

import com.example.bcm.domain.member.model.Member

data class UpdateMemberRequest (
    val id: Long?,
    val email: String,
    val nickname: String,
) {
    fun to(): Member {
        return Member(
            id = id,
            email = email,
            nickname = nickname
        )
    }
}

