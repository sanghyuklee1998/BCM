package com.example.bcm.domain.post.service

import com.example.bcm.domain.global.exception.ModelNotFoundException
import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.model.Post
import com.example.bcm.domain.post.model.toResponse
import com.example.bcm.domain.post.repository.PostRepository
import com.example.bcm.domain.post.repository.PostRepositoryV2
import com.example.bcm.domain.searchkeyword.model.SearchKeyword
import com.example.bcm.domain.searchkeyword.repository.SearchKeywordRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val postRepositoryV2: PostRepositoryV2,
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

//    @Transactional
//    override fun getPostByTitle(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
//        val post = postRepository.findByTitleLike("%$keyword%", PageRequest.of(pageNumber, pageSize))
//
//        if (!post.isEmpty) {
//            val searchKeyword = searchKeywordRepository.findByKeyword(keyword)
//
//
//            if (searchKeyword == null) {
//                val newKeyword = SearchKeyword(keyword = keyword, searchCount = 1)
//                searchKeywordRepository.save(newKeyword)
//            }
//
//            else {
//                searchKeyword.searchCount++
//                searchKeywordRepository.save(searchKeyword)
//            }
//        }
//
//
//        return post.map { it.toResponse() }
//    }
//
//    @Transactional
//    override fun getPostByContent(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
//        val post = postRepository.findByContentLike("%$keyword%", PageRequest.of(pageNumber, pageSize))
//
//        if (!post.isEmpty) {
//            val searchKeyword = searchKeywordRepository.findByKeyword(keyword)
//            if (searchKeyword == null) {
//                val newKeyword = SearchKeyword(keyword = keyword, searchCount = 1)
//                searchKeywordRepository.save(newKeyword)
//            } else {
//                searchKeyword.searchCount++
//                searchKeywordRepository.save(searchKeyword)
//            }
//        }
//        return post.map { it.toResponse() }
//    }

    override fun getPostByTitleOrContent(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val post =
            postRepository.findByTitleContainsOrContentContains(keyword, keyword, PageRequest.of(pageNumber, pageSize))
        if (!post.isEmpty) { // 게시글이 존재하는 경우에만 검색어 저장 로직 실행
            val searchKeyword = searchKeywordRepository.findByKeyword(keyword)
            if (searchKeyword == null) {
                val newKeyword = SearchKeyword(keyword = keyword, searchCount = 1)
                searchKeywordRepository.save(newKeyword)
            } else {
                searchKeyword.searchCount++
                searchKeywordRepository.save(searchKeyword)
            }
        }
        return post.map { it.toResponse() }
    }


    override fun getPostSearchWithCaching(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val post =
            postRepositoryV2.findByTitleContainsOrContentContains(keyword, keyword, PageRequest.of(pageNumber, pageSize))
        if (!post.isEmpty) { // 게시글이 존재하는 경우에만 검색어 저장 로직 실행
            val searchKeyword = searchKeywordRepository.findByKeyword(keyword)
            if (searchKeyword == null) {
                val newKeyword = SearchKeyword(keyword = keyword, searchCount = 1)
                searchKeywordRepository.save(newKeyword)
            } else {
                searchKeyword.searchCount++
                searchKeywordRepository.save(searchKeyword)
            }
        }
        return post.map { it.toResponse() }

    }




    override fun getPostByPage(pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val page = postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNumber, pageSize))
        return page.map { it.toResponse() }
    }

    @Transactional(readOnly = true)
    override fun getTopSearchKeywords(): List<SearchKeyword> {
        val pageable: Pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "searchCount"))
        return searchKeywordRepository.findAll(pageable).content
    }
}