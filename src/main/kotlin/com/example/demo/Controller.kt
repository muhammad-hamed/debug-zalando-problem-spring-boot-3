package com.example.demo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @PostMapping(path = ["/test"])
    suspend fun testEndpoint(
        @RequestHeader("mandatory-header") mandatoryHeader: String,
        @RequestBody body: Body
    ) {
        // return noting
    }
}

data class Body(val firstName: String, val lastName: String)

