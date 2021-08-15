package com.mycompany.deliveryapp.service

import com.mycompany.deliveryapp.model.User
import com.mycompany.deliveryapp.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.EmptyResultDataAccessException
import java.lang.RuntimeException

@SpringBootTest
class LoginServiceImplTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var loginService: LoginService

    @Test
    fun loginUser() {
        var user = User(0, "test", "test", "username", "password", "email")
        userRepository.save(user)

        var result = loginService.loginUser(user.userName,user.password)

        assertTrue(result.isNotBlank())
    }

    @Test
    fun loginUserWithInvalidPassword() {
        var user = User(0, "test", "test", "username", "password", "email")
        userRepository.save(user)


        assertThrows<RuntimeException> {
            loginService.loginUser(user.userName, "fake")
        }
    }

    @Test
    fun loginUserWithInvalidUsername() {
        var user = User(0, "test", "test", "username", "password", "email")
        userRepository.save(user)


        assertThrows<RuntimeException> {
            loginService.loginUser("fake", user.password)
        }
    }
}