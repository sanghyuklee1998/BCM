package com.example.bcm.domain.comment.repository

import com.example.bcm.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}