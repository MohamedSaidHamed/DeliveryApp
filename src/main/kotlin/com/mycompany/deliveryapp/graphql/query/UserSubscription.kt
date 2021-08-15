package com.mycompany.deliveryapp.graphql.query

import com.expediagroup.graphql.server.operations.Subscription
import com.mycompany.deliveryapp.model.User
import com.mycompany.deliveryapp.service.UserService
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserSubscription : Subscription {

    @Autowired
    lateinit var userService: UserService

    fun users(): Publisher<User> {
        return userService.fetchAllUsers()
    }
}