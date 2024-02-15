package com.example.bcm.domain.member.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="member")
class Member(
    @Column(name ="email", nullable = false)
    var email: String,

    @Column(name="nickname", nullable= false)
    var nickname : String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

){

}