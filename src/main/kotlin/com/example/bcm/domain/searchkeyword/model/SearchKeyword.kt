package com.example.bcm.domain.searchkeyword.model

import jakarta.persistence.*

@Entity
@Table(name = "searchKeyword")
class SearchKeyword(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "keyword", nullable = false)
        var keyword: String,

        @Column(name = "searchCount", nullable = false)
        var searchCount: Int = 0
) {

}