package com.example.bcm.domain.comment.model

import com.example.bcm.domain.post.model.Post
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime


@Entity
data class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "content")
    var content: String,

    @Column(name = "nickname")
    var nickname: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post,

    @Column(name = "createdAt")
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()

)