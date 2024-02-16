package com.example.bcm.domain.post.repository

import com.example.bcm.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
    fun findByTitleLike(title: String): List<Post>
    fun findByContentLike(content: String): List<Post>

}