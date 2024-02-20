package com.example.bcm.domain.comment.controller

import com.example.bcm.domain.comment.dto.CommentResponse
import com.example.bcm.domain.comment.dto.CreateCommentRequest
import com.example.bcm.domain.comment.dto.UpdateCommentRequest
import com.example.bcm.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/posts/{postId}/comments")
@RestController
class CommentController (
    private val commentService: CommentService
){

    @PostMapping
    fun createComment(
        @RequestParam postId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {

        val result = commentService.createComment(postId, createCommentRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        val updateComment = commentService.updateComment(commentId, updateCommentRequest)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(updateComment)
    }

    @DeleteMapping(" /{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long
    ): ResponseEntity<String> {

        commentService.deleteComment(commentId)
        val deleteCommentSuccessMessage = "댓글이 성공적으로 삭제되었습니다."

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(deleteCommentSuccessMessage)

    }
}