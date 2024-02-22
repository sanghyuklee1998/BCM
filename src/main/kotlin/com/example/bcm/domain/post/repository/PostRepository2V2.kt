package com.example.bcm.domain.post.repository

import com.example.bcm.domain.post.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostRepository2V2 {

    fun findByTitleContainsOrContentContains(title: String, content: String, pageable: Pageable): Page<Post>
    fun findAllByOrderByCreatedAtDesc(pageable: Pageable): Page<Post>
}