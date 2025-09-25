package br.com.fiap.mindtek.mindtek_api.dto

import java.time.LocalDateTime
import java.util.UUID

data class CreateCheckInRequest(
    val moodScore: Int,
    val emoji: String,
    val comment: String?,
    val extraAnswers: Map<String, String>
)

data class CheckInResponse(
    val checkInId: String,
    val userId: String,
    val date: LocalDateTime,
    val coinsEarned: Int,
    val message: String
)