package com.github.pedroluis02.fixedpdfgenclient

import com.github.pedroluis02.fixedpdfgenclient.service.ServerInfoService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

suspend fun main() {
    val client = HttpClient(CIO) {
        defaultRequest {
            url("http://0.0.0.0:8080/api/v1/")
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
    }

    val service = ServerInfoService(client)
    val info = service.getInfo()
    println(info)

    client.close()
}