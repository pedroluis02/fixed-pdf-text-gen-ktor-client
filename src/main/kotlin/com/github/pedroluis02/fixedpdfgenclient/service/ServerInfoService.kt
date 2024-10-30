package com.github.pedroluis02.fixedpdfgenclient.service

import com.github.pedroluis02.fixedpdfgenclient.model.ServerInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ServerInfoService(private val client: HttpClient) {

    suspend fun getInfo(): ServerInfo = client.get("info").body()
}
