package com.mycompany.deliveryapp

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mycompany.deliveryapp.model.User
import com.mycompany.deliveryapp.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.io.IOException

@SpringBootApplication
class DeliveryApplication{
	@Bean
	fun init(userService: UserService) = CommandLineRunner {
		val mapper = jacksonObjectMapper()
		try {
			val fileContent = this::class.java.classLoader.getResource("users.json").readText()
			val users: List<User> = mapper.readValue<List<User>>(fileContent)
			users.forEach { user -> userService.saveUser(user) }
		} catch (e: IOException) {
			println("Unable to save users: " + e.message)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<DeliveryApplication>(*args)
}
