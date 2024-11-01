package com.github.pedroluis02.fixedpdfgenclient.service

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.io.File

class PdfGenerationService(private val client: HttpClient) {

    suspend fun downloadSample(): File {
        val response = client.post("pdf-generation/sample")

        val contentDispositionValue = response.headers[HttpHeaders.ContentDisposition]
        val contentDisposition = ContentDisposition.parse(contentDispositionValue!!)

        val name = contentDisposition.parameter(ContentDisposition.Parameters.FileName)!!
        val file = File(name)

        val output = response.bodyAsBytes()
        file.writeBytes(output)

        return file
    }
}
