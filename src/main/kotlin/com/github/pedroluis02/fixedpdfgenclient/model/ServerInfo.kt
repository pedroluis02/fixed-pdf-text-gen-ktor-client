package com.github.pedroluis02.fixedpdfgenclient.model

import kotlinx.serialization.Serializable

@Serializable
data class ServerInfo(
    val name: String,
    val version: String
)
