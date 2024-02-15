package com.example.bcm.domain.member.service

import com.example.bcm.domain.exception.ModelNotFoundException
import com.example.bcm.domain.member.dto.CreateMemberRequest
import com.example.bcm.domain.member.dto.MemberResponse
import com.example.bcm.domain.member.dto.UpdateMemberRequest
import com.example.bcm.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {
    override fun createMember(createMemberRequest: CreateMemberRequest): MemberResponse {
        val saveMember = memberRepository.save(createMemberRequest.to())

        return MemberResponse.from(saveMember)
    }

    override fun findById(id: Long): MemberResponse {
        val foundMember = memberRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Member",id)

        return foundMember.let{MemberResponse.from(it)}
    }

    override fun findAll(): List<MemberResponse> {

        return memberRepository.findAll().map {MemberResponse.from(it)}
    }

    override fun updateMember(id: Long,updateMemberRequest: UpdateMemberRequest): MemberResponse {
        val saveMember = memberRepository.save(updateMemberRequest.to())

        return MemberResponse.from(saveMember)
    }

    override fun deleteMember(id: Long) {
        memberRepository.deleteById(id)
    }
}