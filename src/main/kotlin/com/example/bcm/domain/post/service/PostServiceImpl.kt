package com.example.bcm.domain.post.service

import com.example.bcm.domain.exception.ModelNotFoundException
import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.model.Post
import com.example.bcm.domain.post.model.toResponse
import com.example.bcm.domain.post.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
        private val postRepository: PostRepository
):PostService {

    override fun getAllPostList(): List<PostResponse> {
        return postRepository.findAll().map{it.toResponse()}
    }


    @Transactional
    override fun getPostById(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        plusViews(post)
        return post.toResponse()
    }

    private fun plusViews(post: Post) {
        post.views++
    }

    @Transactional
    override fun createPost(request: CreatePostRequest): PostResponse {
        return postRepository.save(
                Post(
                        title= request.title,
                        content = request.content
                )
        ).toResponse()
    }

    @Transactional
    override fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId)?: throw ModelNotFoundException("Post", postId)
        post.title = request.title
        post.content = request.content
        return postRepository.save(post).toResponse()
    }

    @Transactional
    override fun deletePost(postId: Long) {
        val post= postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        postRepository.delete(post)
    }


    override fun getPostByTitle (keyword: String): List<PostResponse> {
        val post = postRepository.findByTitleLike("%"+keyword+"%")
        return post.map { it.toResponse() }
    }

    override fun getPostByContent(keyword: String): List<PostResponse> {
        val post = postRepository.findByContentLike("%"+keyword+"%")
        return post.map { it.toResponse() }
    }

    override fun getPostByPage(pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val page = postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNumber, pageSize))
        return page.map { it.toResponse() }
    }
}