package com.example.bcm.domain.searchkeyword.repository

import com.example.bcm.domain.searchkeyword.model.SearchKeyword
import org.springframework.data.jpa.repository.JpaRepository

interface SearchKeywordRepository: JpaRepository<SearchKeyword, Long> {

    fun findByKeyword(keyword:String): SearchKeyword?
}