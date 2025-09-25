package br.com.fiap.mindtek.mindtek_api.service // CORRIGIDO

import br.com.fiap.mindtek.mindtek_api.dto.CreateCheckInRequest // CORRIGIDO
import br.com.fiap.mindtek.mindtek_api.dto.CheckInResponse // CORRIGIDO
import br.com.fiap.mindtek.mindtek_api.entity.CheckIn // CORRIGIDO
import br.com.fiap.mindtek.mindtek_api.repository.CheckInRepository // CORRIGIDO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service

@Service
class CheckInService(private val repository: CheckInRepository) {

    fun create(userId: String, request: CreateCheckInRequest): CheckInResponse {
        val extraAnswersJson = jacksonObjectMapper().writeValueAsString(request.extraAnswers)

        val checkIn = CheckIn(
            userId = userId,
            moodScore = request.moodScore,
            emoji = request.emoji,
            comment = request.comment,
            extraAnswers = extraAnswersJson
        )

        val savedCheckIn = repository.save(checkIn)
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