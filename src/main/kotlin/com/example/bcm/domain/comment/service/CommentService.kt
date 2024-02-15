package com.example.bcm.domain.comment.service

import com.example.bcm.domain.comment.dto.CommentResponse
import com.example.bcm.domain.comment.dto.CreateCommentRequest
import com.example.bcm.domain.comment.dto.UpdateCommentRequest
import com.example.bcm.domain.comment.model.Comment

interface CommentService {

    fun createComment (postId: Long, createCommentRequest: CreateCommentRequest): CommentResponse

    fun updateComment (commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun deleteComment (commentId: Long)

    fun getCommentById(commentId: Long): Comment
}