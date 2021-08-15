package com.mycompany.deliveryapp.graphql.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.mycompany.deliveryapp.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserMutation: Mutation {

    @Autowired
    lateinit var loginService: LoginService

    fun login(username :String, password: String):String{
        return loginService.loginUser(username,password)
    }
}