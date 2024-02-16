package com.example.bcm.domain.post.controller

import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.service.PostService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostController(
    private val postService: PostService
) {

    @GetMapping()
    fun getPostList(): ResponseEntity<List<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getAllPostList())
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostById(postId))
    }

    @PostMapping
    fun createPost(@RequestBody createPostRequest: CreatePostRequest): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(createPostRequest))
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody updatePostRequest: UpdatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(postId, updatePostRequest))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Unit> {
        postService.deletePost(postId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @GetMapping("/test")
    fun getPostByTitle(
        @RequestParam keyword: String
    ): ResponseEntity<List<PostResponse>> {
        postService.getPostByTitle(keyword)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostByTitle(keyword))
    }

    @GetMapping("/test2")
    fun getPostByContent(
        @RequestParam keyword: String
    ): ResponseEntity<List<PostResponse>> {
        postService.getPostByContent(keyword)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostByContent(keyword))
    }

    @GetMapping("/page")
    fun getPostByPage(
        @RequestParam(defaultValue = "0") pageNumber: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostByPage(pageNumber + 1, pageSize))
    }
}