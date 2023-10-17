package com.example.demo.config

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.jackson.ProblemModule
import org.zalando.problem.violations.ConstraintViolationProblemModule

@Configuration
class JacksonConfiguration {

    @Bean
    fun customizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .serializationInclusion(NON_NULL)
                .modulesToInstall(
                    ProblemModule(),
                    ConstraintViolationProblemModule(),
                )
        }
    }
}
