package com.mycompany.deliveryapp.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class LoginServiceImpl : LoginService {

    @Autowired
    lateinit var userService: UserService

    override fun loginUser(username: String, password: String): String {
        var user = userService.getUser(username)
        if(password != user.password){
            throw RuntimeException("Invalid username or password")
        }
        var token = JWT.create()
            .withSubject(user.userName)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000))
            .sign(Algorithm.HMAC512("SECRET_KEY"))

        return token
    }
}