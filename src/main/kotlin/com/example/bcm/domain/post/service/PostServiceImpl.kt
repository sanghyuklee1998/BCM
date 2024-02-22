package com.example.bcm.domain.post.service

import com.example.bcm.domain.global.exception.ModelNotFoundException
import com.example.bcm.domain.post.dto.CreatePostRequest
import com.example.bcm.domain.post.dto.PostResponse
import com.example.bcm.domain.post.dto.UpdatePostRequest
import com.example.bcm.domain.post.model.Post
import com.example.bcm.domain.post.model.toResponse
import com.example.bcm.domain.post.repository.PostRepository
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

        if (!post.isEmpty) {
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
        }

        //데이터베이스에서 해당 키워드 조회
        return post.map { it.toResponse() }
    }

    @Transactional
    override fun getPostByContent(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val post = postRepository.findByContentLike("%$keyword%", PageRequest.of(pageNumber, pageSize))

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


    override fun getPostByPage(pageNumber: Int, pageSize: Int): Page<PostResponse> {
        val page = postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNumber, pageSize))
        return page.map { it.toResponse() }
    }

    @Transactional(readOnly = true)
    override fun getTopSearchKeywords(): List<SearchKeyword> {
        val pageable: Pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "searchCount"))
        return searchKeywordRepository.findAll(pageable).content
    }

    // 캐시에 값이 있는가?
        // 1. Cacheable에서 캐시에 있는 값을 리턴해준다.
        // 2. 메소드 바디는 실행하지 않는다.
    // 캐시에 값이 없는가?
        // 1. Cacheable에서 캐시에 값이 없으니까 메소드 바디를 호출해서 값을 떼온다.

    // AOP 방식이기 때문 + AOP > Reflection
    // cache의 Key는 keyword + pageNumber + pageSize
    // value = CacheNames의 Alias, CacheNames = value의 Alias ==> CacheNames, value는 캐시의 이름을 말한다.
    @Cacheable(value = ["postByTitleORContent"], key = "''")
    override fun getPostSearchWithCaching(keyword: String, pageNumber: Int, pageSize: Int): Page<PostResponse> {
        return postRepository.findByTitleContainsOrContentContains(
            keyword,
            keyword,
            PageRequest.of(pageNumber, pageSize)
        ).map { it.toResponse() }
    }

    // 스케줄링을 적용하거나, 나처럼 C, U, D 상황이 나왔을 때 캐시를 삭제하게끔 하거나
}