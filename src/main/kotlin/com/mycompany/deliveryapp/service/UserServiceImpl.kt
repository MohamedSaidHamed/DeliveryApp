package com.mycompany.deliveryapp.service

import com.mycompany.deliveryapp.model.User
import com.mycompany.deliveryapp.repository.UserRepository
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.util.function.Supplier

@Service
class UserServiceImpl() : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    /**
     * a method to return user by its username
     */

    override fun getUser(username: String): User {
    return  userRepository.findByUserName(username)
    }

    /**
     * a method to save new user. used on application startup to fill H2 DB with values.
     */
    override fun saveUser(user: User) {
        userRepository.save(user)
    }

    /**
     * Fetch all users from DB and wrap it with Flux
     */
    override fun fetchAllUsers(): Flux<User> {
        val defer: Flux<User> = Flux.defer {
            Flux.fromIterable(
                this.userRepository.findAll()
            )
        }
        return defer.subscribeOn(Schedulers.parallel())
    }

    /**
     * Fetch all users from DB and return it as a list<User>
     */
    override fun fetchAllUsersAsList(): List<User> {
        return userRepository.findAll().toList()
    }
}