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


    override fun getUser(username: String): User {
    return  userRepository.findByUserName(username)
    }

    override fun saveUser(user: User) {
        userRepository.save(user)
    }

    override fun fetchAllUsers(): Flux<User> {
        val defer: Flux<User> = Flux.defer(Supplier<Publisher<User>> {
            Flux.fromIterable(
                this.userRepository.findAll()
            )
        })
        return defer.subscribeOn(Schedulers.parallel())
    }

    override fun fetchAllUsersAsList(): List<User> {
        return userRepository.findAll().toList()
    }
}