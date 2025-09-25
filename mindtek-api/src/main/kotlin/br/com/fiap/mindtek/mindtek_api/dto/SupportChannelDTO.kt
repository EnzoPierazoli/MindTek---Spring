package br.com.fiap.mindtek.mindtek_api.dto

data class SupportChannelDTO(
    val title: String,
    val description: String,
    val contact: String?,
    val link: String?,
    val type: String // "email", "link", "phone", etc.
)