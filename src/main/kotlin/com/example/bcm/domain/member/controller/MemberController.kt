package com.example.bcm.domain.member.controller

import com.example.bcm.domain.member.dto.CreateMemberRequest
import com.example.bcm.domain.member.dto.MemberResponse
import com.example.bcm.domain.member.dto.UpdateMemberRequest
import com.example.bcm.domain.member.model.Member
import com.example.bcm.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    val memberService: MemberService
) {

    @PostMapping
    fun createMember(
        @RequestBody createMemberRequest: CreateMemberRequest
    ): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.createMember(createMemberRequest))
    }


    @GetMapping("/{memberId}")
    fun findMember(
        @PathVariable memberId: Long
    ): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.findById(memberId))
    }

    @GetMapping
    fun findAllMember(): ResponseEntity<List<MemberResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.findAll())
    }



    @PutMapping("/{memberId}")
    fun updateMember(
        @PathVariable memberId: Long,
        @RequestBody updateMemberRequest: UpdateMemberRequest
    ): ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.updateMember(memberId,updateMemberRequest))
    }

    @DeleteMapping("/{memberId}")
    fun deleteMemeber(
        @PathVariable memberId: Long
    ): ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}