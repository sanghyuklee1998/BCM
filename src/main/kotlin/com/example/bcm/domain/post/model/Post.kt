package com.example.bcm.domain.post.model

import com.example.bcm.domain.post.dto.PostResponse
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime


@Entity
@Table(name = "post")
class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "content", nullable = false)
        var content: String,

        @Column
        @CreatedDate
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @Column var views: Int = 0

)

fun Post.toResponse(): PostResponse {
    return PostResponse(
            id = id!!,
            title = title,
            content = content,
            createdAt = createdAt,
            views = views)
}