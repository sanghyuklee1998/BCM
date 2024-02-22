package com.example.bcm.domain.comment.service

import com.example.bcm.domain.comment.dto.CommentResponse
import com.example.bcm.domain.comment.dto.CreateCommentRequest
import com.example.bcm.domain.comment.dto.UpdateCommentRequest
import com.example.bcm.domain.comment.model.Comment
import com.example.bcm.domain.comment.repository.CommentRepository
import com.example.bcm.domain.global.exception.ModelNotFoundException
import com.example.bcm.domain.global.exception.TargetNotFoundException
import com.example.bcm.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class CommentServiceImpl(
        private val commentRepository: CommentRepository,
        private val postRepository: PostRepository
) : CommentService {

    @Transactional
    override fun createComment(postId: Long, createCommentRequest: CreateCommentRequest
    ): CommentResponse {
        val targetPost = postRepository.findByIdOrNull(postId)
                ?: throw TargetNotFoundException("target post is not found")

        val comment = Comment(
                content = createCommentRequest.content,
                post = targetPost,
                createdAt = LocalDateTime.now(),
        )

        val result = commentRepository.save(comment)

        return CommentResponse
                .toCommentResponse(result)
    }

    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest
    ): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        comment.content = request.content

        return CommentResponse.toCommentResponse(comment)
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        commentRepository.delete(comment)
    }

    override fun getCommentById(commentId: Long): Comment {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        return comment
    }


}