package com.example.bcm.domain.post.repository

import com.example.bcm.domain.post.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long> {
    fun findByTitleLike(title: String): List<Post>
    fun findByContentLike(content: String): List<Post>

    fun findAllByOrderByIdDesc(pageable: Pageable): Page<Post>

}