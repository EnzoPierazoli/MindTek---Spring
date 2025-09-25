package br.com.fiap.mindtek.mindtekapi.service

import br.com.fiap.mindtek.mindtekapi.dto.CreateCheckInRequest
import br.com.fiap.mindtek.mindtekapi.dto.CheckInResponse
import br.com.fiap.mindtek.mindtekapi.entity.CheckIn
import br.com.fiap.mindtek.mindtekapi.repository.CheckInRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service

@Service
class CheckInService(private val repository: CheckInRepository) {

    fun create(userId: String, request: CreateCheckInRequest): CheckInResponse {
        // Converte o mapa de respostas extras para uma String JSON
        val extraAnswersJson = jacksonObjectMapper().writeValueAsString(request.extraAnswers)

        val checkIn = CheckIn(
            userId = userId,
            moodScore = request.moodScore,
            emoji = request.emoji,
            comment = request.comment,
            extraAnswers = extraAnswersJson
        )

        val savedCheckIn = repository.save(checkIn)

        // Lógica de gamificação (pode ser aprimorada depois)
        val coins = 50

        return CheckInResponse(
            checkInId = savedCheckIn.id!!,
            userId = savedCheckIn.userId,
            date = savedCheckIn.createdAt,
            coinsEarned = coins,
            message = "Check-in registrado com sucesso."
        )
    }

    fun findHistoryByUserId(userId: String): List<CheckIn> {
        return repository.findByUserIdOrderByCreatedAtDesc(userId)
    }
}