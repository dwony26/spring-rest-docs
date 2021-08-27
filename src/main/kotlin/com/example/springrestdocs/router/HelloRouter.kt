package com.example.springrestdocs.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class HelloRouter {
    @Bean
    fun route() = coRouter {
        GET("hello") { ServerResponse.ok().bodyValueAndAwait("world") }
    }
}
