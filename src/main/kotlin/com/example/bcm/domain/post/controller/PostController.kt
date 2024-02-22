package com.example.bcm.domain.post.controller

import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.service.PostService
import com.example.bcm.domain.searchkeyword.model.SearchKeyword
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
    fun deletePost(@PathVariable postId: Long): ResponseEntity<String> {
        postService.deletePost(postId)
        val deletePostSuccessMessage = "게시글이 성공적으로 삭제되었습니다."
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(deletePostSuccessMessage)
    }

//    @GetMapping("/search/title")
//    fun getPostByTitle(
//            @RequestParam keyword: String,
//            @RequestParam(defaultValue = "1") pageNumber: Int,
//            @RequestParam(defaultValue = "10") pageSize: Int
//    ): ResponseEntity<Page<PostResponse>> {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(postService.getPostByTitle(keyword, pageNumber -1, pageSize))
//    }
//
//    @GetMapping("/search/content")
//    fun getPostByContent(
//            @RequestParam keyword: String,
//            @RequestParam(defaultValue = "1") pageNumber: Int,
//            @RequestParam(defaultValue = "10") pageSize: Int
//    ): ResponseEntity<Page<PostResponse>> {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(postService.getPostByContent(keyword, pageNumber -1, pageSize))
//    }

    @GetMapping("/search")
    fun getPostByTitleOrContent(
        @RequestParam keyword: String,
        @RequestParam(defaultValue = "1") pageNumber: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostByTitleOrContent(keyword, pageNumber -1, pageSize))
    }

    @GetMapping("/page")
    fun getPostByPage(
            @RequestParam(defaultValue = "1") pageNumber: Int,
            @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PostResponse>> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPostByPage(pageNumber - 1, pageSize))
    }

    @GetMapping("/top-search-keywords")
    fun getTopSearchKeywords(): ResponseEntity<List<SearchKeyword>> {
        val topKeyword = postService.getTopSearchKeywords()
        return ResponseEntity.ok(topKeyword)
    }


}