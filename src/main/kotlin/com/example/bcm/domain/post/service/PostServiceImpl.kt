package com.example.bcm.domain.post.service

import com.example.bcm.domain.exception.ModelNotFoundException
import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.model.Post
import com.example.bcm.domain.post.model.toResponse
import com.example.bcm.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class PostServiceImpl(
        private val postRepository: PostRepository
):PostService {

    override fun getAllPostList(): List<PostResponse> {
        return postRepository.findAll().map{it.toResponse()}
    }

    override fun getPostById(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        return post.toResponse()
    }

    override fun createPost(request: CreatePostRequest): PostResponse {
        return postRepository.save(
                Post(
                        title= request.title,
                        content = request.content,
                        createdAt = ZonedDateTime.now()
                )
        ).toResponse()
    }

    override fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId)?: throw ModelNotFoundException("Post", postId)
        post.title = request.title
        post.content = request.content
        return postRepository.save(post).toResponse()
    }

    override fun deletePost(postId: Long) {
        val post= postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        postRepository.delete(post)
    }
}