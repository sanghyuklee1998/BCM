package com.example.bcm.domain.member.controller

import com.example.bcm.domain.member.dto.CreateMemberRequest
import com.example.bcm.domain.member.dto.MemberResponse
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
class MemberController(
    val memberService: MemberService
) {

    @PostMapping("/signup")
    fun createMember(
        @RequestBody createMemberRequest: CreateMemberRequest
    ): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(MemberService.createMember(createMemberRequest))
    }


    @GetMapping("/members")
    fun findMember(@PathVariable memberId: Long): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.findById(memberId))
    }

    @GetMapping("/{memberId}")
    fun findAllMember()
        @PathVariable


    @PutMapping("/{memberId}")
    fun updateMember(
        @PathVariable memberId: Long,
    ){

    }

    @DeleteMapping("/{mamberId}")
    fun deleteMemeber(

    ){

    }

}