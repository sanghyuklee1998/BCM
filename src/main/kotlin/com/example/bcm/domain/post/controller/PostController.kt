package com.example.bcm.domain.post.controller

import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.service.PostService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/posts")
class PostController (
        private val postService: PostService
){

    @GetMapping()
    fun getPostList(): ResponseEntity<List<PostResponse>>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getAllPostList())
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long) : ResponseEntity<PostResponse>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPostById(postId))
    }

    @PostMapping
    fun createPost (@RequestBody createPostRequest: CreatePostRequest) : ResponseEntity<PostResponse>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.createPost(createPostRequest))
    }

    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody updatePostRequest: UpdatePostRequest) : ResponseEntity<PostResponse>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.updatePost(postId,updatePostRequest))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId:Long) : ResponseEntity<Unit>{
        postService.deletePost(postId)
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }

    @GetMapping("/test")
    fun getPostByTitle(
        @RequestParam keyword: String): ResponseEntity<List<PostResponse>>{
//        65,68번 줄 중복으로 키워드 count가 2씩 되어서 삭제했습니다
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostByTitle(keyword))
    }

    @GetMapping("/test2")
    fun getPostByContent(
        @RequestParam keyword: String): ResponseEntity<List<PostResponse>> {
//        중복으로 count 2 되어 삭제
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostByContent(keyword))
    }

    @GetMapping("/page")
    fun getPostByPage(
        @RequestParam pageNumber: Int,
        @RequestParam pageSize: Int): ResponseEntity<Page<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostByPage(pageNumber, pageSize))
    }
}