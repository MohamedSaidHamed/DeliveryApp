package com.mycompany.deliveryapp.service

import com.mycompany.deliveryapp.model.User
import reactor.core.publisher.Flux

interface UserService {
    fun getUser(username: String):User

    fun saveUser(user: User)

    fun fetchAllUsers(): Flux<User>

    fun fetchAllUsersAsList(): List<User>
}