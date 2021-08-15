package com.mycompany.deliveryapp.graphql.query

import com.expediagroup.graphql.server.operations.Query
import com.mycompany.deliveryapp.model.User
import com.mycompany.deliveryapp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQuery : Query{

    @Autowired
    lateinit var userService: UserService

    fun users() : List<User> {
        return userService.fetchAllUsersAsList()
    }
}