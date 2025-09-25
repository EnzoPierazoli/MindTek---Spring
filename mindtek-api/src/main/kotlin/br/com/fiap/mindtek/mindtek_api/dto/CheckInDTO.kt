package br.com.fiap.mindtek.mindtekapi.dto

import java.time.LocalDateTime
import java.util.UUID

// DTO para a requisição (o que o app Android vai enviar)
data class CreateCheckInRequest(
    val moodScore: Int,
    val emoji: String,
    val comment: String?,
    val extraAnswers: Map<String, String>
)

// DTO para a resposta (o que o servidor vai devolver)
data class CheckInResponse(
    val checkInId: UUID,
    val userId: String,
    val date: LocalDateTime,
    val coinsEarned: Int,
    val message: String
)