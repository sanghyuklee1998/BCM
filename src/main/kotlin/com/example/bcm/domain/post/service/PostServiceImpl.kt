package com.example.bcm.domain.post.service

import com.example.bcm.domain.exception.ModelNotFoundException
import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.model.Post
import com.example.bcm.domain.post.model.toResponse
import com.example.bcm.domain.post.repository.PostRepository
import com.example.bcm.domain.searchkeyword.model.SearchKeyword
import com.example.bcm.domain.searchkeyword.repository.SearchKeywordRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
        private val postRepository: PostRepository,
        private val searchKeywordRepository: SearchKeywordRepository
) : PostService {

    override fun getAllPostList(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
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
                        title = request.title,
                        content = request.content
                )
        ).toResponse()
    }

    @Transactional
    override fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        post.title = request.title
        post.content = request.content
        return postRepository.save(post).toResponse()
    }

    @Transactional
    override fun deletePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        postRepository.delete(post)
    }

    @Transactional
    override fun getPostByTitle(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val post = postRepository.findByTitleLike("%$keyword%", PageRequest.of(pageNumber, pageSize))

        //데이터베이스에서 해당 키워드 조회
        val searchKeyword = searchKeywordRepository.findByKeyword(keyword)

        //조회된 키워드가 없으면 새로 만들어서 데이터베이스에 저장
        if (searchKeyword == null) {
            val newKeyword = SearchKeyword(keyword = keyword, searchCount = 1)
            searchKeywordRepository.save(newKeyword)
        }
        //저장된 키워드 count +1 증가
        else {
            searchKeyword.searchCount++
            searchKeywordRepository.save(searchKeyword)
        }
        return post.map { it.toResponse() }
    }

    @Transactional
    override fun getPostByContent(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val post = postRepository.findByContentLike("%$keyword%", PageRequest.of(pageNumber, pageSize))
        val searchKeyword = searchKeywordRepository.findByKeyword(keyword)
        if (searchKeyword == null) {
            val newKeyword = SearchKeyword(keyword = keyword, searchCount = 1)
            searchKeywordRepository.save(newKeyword)
        } else {
            searchKeyword.searchCount++
            searchKeywordRepository.save(searchKeyword)
        }
        return post.map { it.toResponse() }
    }

    override fun getPostByTitleOrContent(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val post = postRepository.findByTitleContainsOrContentContains(keyword, keyword, PageRequest.of(pageNumber, pageSize))
        val searchKeyword = searchKeywordRepository.findByKeyword(keyword)
        if (searchKeyword == null) {
            val newKeyword = SearchKeyword(keyword = keyword, searchCount = 1)
            searchKeywordRepository.save(newKeyword)
        } else {
            searchKeyword.searchCount++
            searchKeywordRepository.save(searchKeyword)
        }
        return post.map { it.toResponse() }
    }


    override fun getPostByPage(pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val page = postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNumber, pageSize))
        return page.map { it.toResponse() }
    }

}