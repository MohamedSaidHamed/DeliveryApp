package com.mycompany.deliveryapp.service

interface LoginService {
    fun loginUser(username: String, password: String) :String
}