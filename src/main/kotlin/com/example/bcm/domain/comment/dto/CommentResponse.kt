package com.example.bcm.domain.comment.dto

import com.example.bcm.domain.comment.model.Comment
import java.time.LocalDateTime
import java.time.ZonedDateTime

class CommentResponse (
    var id: Long?,
    var content: String,
    var createdAt: LocalDateTime,
) {
    companion object {
        fun toCommentResponse(comment: Comment
        ): CommentResponse {
            return CommentResponse(
                id = comment.id,
                content = comment.content,
                createdAt = comment.createdAt
            )
        }
    }
}