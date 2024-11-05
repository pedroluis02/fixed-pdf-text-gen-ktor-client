package com.github.pedroluis02.fixedpdfgenclient

import com.github.pedroluis02.fixedpdfgenclient.service.PdfGenerationService
import com.github.pedroluis02.fixedpdfgenclient.service.ServerInfoService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Path

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

    downloadPdfFromTemplate(client)
    client.close()
}

private suspend fun getServerInfo(client: HttpClient) {
    val service = ServerInfoService(client)
    val info = service.getInfo()
    println(info)
}

private suspend fun downloadPdfSample(client: HttpClient) {
    val service = PdfGenerationService(client)
    val file = service.downloadSample()
    println("sample file: $file")
}

private suspend fun downloadPdfFromTemplate(client: HttpClient) {
    val templateJson = readTemplateJson()

    val service = PdfGenerationService(client)
    val file = service.downloadFromTemplate(templateJson)
    println("template file: $file")
}

private fun readTemplateJson(): String {
    return Files.readString(Path.of("pdf-template.json"))
}
