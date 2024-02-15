package com.example.bcm.domain.member.service

import com.example.bcm.domain.member.dto.CreateMemberRequest
import com.example.bcm.domain.member.dto.MemberResponse
import com.example.bcm.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberSerivceImpl(
    private val memberRepository: MemberRepository
): MemberService {
    override fun createMember(createMemberRequest: CreateMemberRequest): MemberResponse {
        TODO("Not yet implemented")
    }
}