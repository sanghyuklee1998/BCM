package com.example.bcm.domain.post.controller

import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.service.PostService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/posts")
class PostControllerV2(
    private val postService: PostService
) {
    @GetMapping("/search")
    fun getPostByTitleOrContent(
        @RequestParam keyword: String,
        @RequestParam(defaultValue = "1") pageNumber: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostSearchWithCaching(keyword, pageNumber -1, pageSize))
    }
}