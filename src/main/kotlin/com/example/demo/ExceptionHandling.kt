package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.server.WebExceptionHandler
import org.zalando.problem.spring.webflux.advice.ProblemExceptionHandler
import org.zalando.problem.spring.webflux.advice.ProblemHandling

@Configuration
@ControllerAdvice
class ExceptionHandling : ProblemHandling {
    @Bean
    // The handler must have precedence over WebFluxResponseStatusExceptionHandler
    // and Spring Boot's ErrorWebExceptionHandler
    @Order(-2)
    fun problemExceptionHandler(mapper: ObjectMapper, problemHandling: ProblemHandling): WebExceptionHandler {
        return ProblemExceptionHandler(mapper, problemHandling)
    }
}
