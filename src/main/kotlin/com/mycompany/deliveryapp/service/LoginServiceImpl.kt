package com.mycompany.deliveryapp.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class LoginServiceImpl : LoginService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var userService: UserService


/**
 * validate username and password and return JWT token. Ideally a password should have some hashing mechanism.
 * No plain text passwords should be saved to DB
 */
    override fun loginUser(username: String, password: String): String {
        val user = userService.getUser(username)
        if(password != user.password){
            logger.error("Invalid password")
            throw RuntimeException("Invalid username or password")
        }
        val token = JWT.create()
            .withSubject(user.userName)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000))
            .sign(Algorithm.HMAC512("SECRET_KEY"))

        return token
    }
}