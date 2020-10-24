package com.bol.dangerous

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.util.JdkIdGenerator

@SpringBootApplication
class DangerousKotlinApplication {
	@Bean
	fun idGenerator() = JdkIdGenerator()
}

fun main(args: Array<String>) {
	runApplication<DangerousKotlinApplication>(*args)
}
