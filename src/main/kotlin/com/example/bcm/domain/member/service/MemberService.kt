package com.example.bcm.domain.member.service

import com.example.bcm.domain.member.dto.CreateMemberRequest
import com.example.bcm.domain.member.dto.MemberResponse
import com.example.bcm.domain.member.dto.UpdateMemberRequest

interface MemberService {
    fun createMember(createMemberRequest: CreateMemberRequest): MemberResponse

    fun findById(id: Long): MemberResponse

    fun findAll(): List<MemberResponse>

    fun updateMember(id:Long ,updateMemberRequest: UpdateMemberRequest): MemberResponse

    fun deleteMember(id: Long)
}