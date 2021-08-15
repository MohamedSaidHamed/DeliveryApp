package com.mycompany.deliveryapp.repository

import com.mycompany.deliveryapp.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Int>{
     fun findByUserName(username: String): User
}