package com.example.bcm.domain.comment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.time.ZonedDateTime


@Entity
data class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "contnent")
    var content: String,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "createdAt")
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()

)