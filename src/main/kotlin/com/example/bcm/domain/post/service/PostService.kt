package com.example.bcm.domain.post.service

import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.model.Post

interface PostService {
    fun getPostById(postId:Long): PostResponse
    fun getAllPostList(): List<PostResponse>
    fun createPost(request: CreatePostRequest): PostResponse
    fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse
    fun deletePost(postId: Long)
    fun getPostByTitle (keyword: String): List<PostResponse>
    fun getPostByContent (keyword: String): List<PostResponse>
}