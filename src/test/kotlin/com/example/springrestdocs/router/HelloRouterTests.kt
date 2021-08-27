package com.example.springrestdocs.router

import io.kotest.core.spec.style.WordSpec
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration
import org.springframework.test.web.reactive.server.WebTestClient

class HelloRouterTests : WordSpec({
    val restDocumentation = ManualRestDocumentation()
    val webTestClient = WebTestClient.bindToRouterFunction(HelloRouter().route())
        .configureClient()
        .filter(documentationConfiguration(restDocumentation))
        .build()

    beforeEach {
        restDocumentation.beforeTest(javaClass, "HelloRouterTests")
    }

    afterEach {
        restDocumentation.afterTest()
    }

    "hello" should {
        "return world" {
            webTestClient.get().uri("/hello").exchange().expectStatus().isOk.expectBody()
                .consumeWith(
                    document("hello",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                    )
                )
        }
    }
})
