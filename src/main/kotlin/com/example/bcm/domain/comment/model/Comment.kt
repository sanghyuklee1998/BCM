package com.example.bcm.domain.comment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.annotation.CreatedDate
import java.time.ZonedDateTime


@Entity
class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "contnent")
    var content: String,

    @Column
    var nickname: String,

    @Column
    @CreatedDate
    var createdAt: ZonedDateTime

)