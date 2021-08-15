package com.mycompany.deliveryapp.model

import java.io.Serializable
import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    val firstName: String,
    @Column(nullable = false)
    val lastName: String,
    @Column(name="userName", nullable = false)
    val userName: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val email: String
) : Serializable
