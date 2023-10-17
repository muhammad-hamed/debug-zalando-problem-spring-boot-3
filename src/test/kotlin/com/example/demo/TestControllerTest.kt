package com.example.demo

import com.example.demo.config.JacksonConfiguration
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.zalando.problem.Problem

@OptIn(ExperimentalCoroutinesApi::class)
@WebFluxTest(Controller::class)
@AutoConfigureWebTestClient
@Import(JacksonConfiguration::class)
class TestControllerTest @Autowired constructor(
    private val webTestClient: WebTestClient
) {

    @Test
    fun `should return relevant problem details in case of invalid body being sent`() = runTest {
        // given
        val invalidJson = """
            {
              "first_name": "John"
            }
        """.trimIndent()


        // when
        webTestClient.post()
            .uri("/test")
            .contentType(MediaType.APPLICATION_JSON)
            .header("mandatory-header", "value")
            .bodyValue(invalidJson)
            .exchange()
            // then
            .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
            .expectBody<Problem>()
            .consumeWith { res ->
                val problem = res.responseBody!!
                println("bla ${problem.status}")
                MatcherAssert.assertThat(problem.title, Matchers.equalTo("Bad Request"))
                MatcherAssert.assertThat(problem.detail, Matchers.containsString("400 BAD_REQUEST"))
                MatcherAssert.assertThat(
                    problem.detail,
                    Matchers.containsString("value failed for JSON property last_name due to missing")
                )
            }
    }
}
