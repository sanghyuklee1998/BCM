package com.example.bcm.domain.searchkeyword.repository

import com.example.bcm.domain.searchkeyword.model.SearchKeyword
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface SearchKeywordRepository: JpaRepository<SearchKeyword, Long> {
    //키워드를 이용해서 엔티티를 조회하는 메소드
    fun findByKeyword(keyword:String): SearchKeyword?
}