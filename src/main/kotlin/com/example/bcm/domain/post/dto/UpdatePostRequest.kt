package com.example.bcm.domain.post.dto

data class UpdatePostRequest (
        val id: String,
        val title:String,
        val content: String
)