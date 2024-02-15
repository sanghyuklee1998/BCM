package com.example.bcm.domain.member.controller

import com.example.bcm.domain.member.service.MemberService
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

    @GetMapping
    fun findAllMember(

    )

    @GetMapping("/{memberId}")
    fun findMember(
        @PathVariable
    )

    @PutMapping("/{memberId}")
    fun updateMember(
        @PathVariable memberId: Long,
    )

}