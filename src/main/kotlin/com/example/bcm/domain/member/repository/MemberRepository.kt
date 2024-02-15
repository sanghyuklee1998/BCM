package com.example.bcm.domain.member.repository

import com.example.bcm.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByEmail(email: String): Member?

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean
}