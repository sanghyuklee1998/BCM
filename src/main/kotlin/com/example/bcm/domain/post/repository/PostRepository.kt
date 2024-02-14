package com.example.bcm.domain.post.repository

import com.example.bcm.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {

}