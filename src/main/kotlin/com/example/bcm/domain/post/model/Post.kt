package com.example.bcm.domain.post.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.ZonedDateTime


@Entity
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column
    var nickname: String,

    @Column
    @CreatedDate
    var createdAt: ZonedDateTime


)