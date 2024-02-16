package com.example.bcm.domain.member.dto

import com.example.bcm.domain.member.model.Member

data class UpdateMemberRequest (
    val nickname: String,
) {
    fun to(): Member {
        return Member(
            nickname = nickname
        )
    }
}

