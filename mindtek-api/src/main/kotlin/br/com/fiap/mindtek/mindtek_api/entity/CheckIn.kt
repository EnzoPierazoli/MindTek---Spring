package br.com.fiap.mindtek.mindtek_api.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document(collection = "check_ins")
data class CheckIn(
    @Id
    val id: String? = null,

    val userId: String,
    val moodScore: Int,
    val emoji: String,
    val comment: String?,

    val extraAnswers: Map<String, String>,

    val createdAt: LocalDateTime = LocalDateTime.now()
)