package com.mycompany.deliveryapp.service


import com.mycompany.deliveryapp.model.User
import com.mycompany.deliveryapp.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.EmptyResultDataAccessException
import reactor.test.StepVerifier

@SpringBootTest
 class UserServiceImplTest{

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userService: UserService

    @Test
    fun getUser() {
        var user = User(0, "test", "test", "username", "password", "email")
        userRepository.save(user)

        var result: User = userService.getUser("username")

        assertEquals(result.userName, user.userName)
    }

    @Test
    fun getUserWithException() {
        assertThrows<EmptyResultDataAccessException> {
            userService.getUser("fakeUsername")
        }
    }

    @Test
    fun saveUser() {
        var actual = User(0, "test", "test", "username", "password", "email")
        userService.saveUser(actual)

        var result = userRepository.findByUserName(actual.userName)

        assertEquals(result.userName,actual.userName)
    }

    @Test
    fun fetchAllUsers() {
        var actual = User(0, "test", "test", "username", "password", "email")
        userRepository.save(actual)

        var result = userService.fetchAllUsers()
        StepVerifier
            .create(result)
            .expectNextCount(11)
            .expectNext(actual)
            .expectComplete()
            .verify()
    }

    @Test
    fun fetchAllUsersAsList() {
        var actual = User(0, "test", "test", "username", "password", "email")
        userRepository.save(actual)
        var result = userService.fetchAllUsersAsList()
        assertTrue(result.contains(actual))
    }
}