package com.example.bcm.domain.post.repository

import com.example.bcm.domain.post.model.Post
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository



interface PostRepositoryV2: JpaRepository<Post, Long> {
//    fun findByTitleLike(title: String, pageable: Pageable): Page<Post>
//    fun findByContentLike(content: String, pageable: Pageable): Page<Post>

    fun findByTitleContainsOrContentContains(title: String, content: String, pageable:Pageable): Page<Post>

}