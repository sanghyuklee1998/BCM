package com.example.bcm.domain.comment.dto

import com.example.bcm.domain.comment.model.Comment

class CommentResponse (
    var id: Long?,
    var content: String
) {
    companion object {
        fun toCommentResponse(comment: Comment
        ): CommentResponse {
            return CommentResponse(
                id = comment.id,
                content = comment.content
            )
        }
    }
}