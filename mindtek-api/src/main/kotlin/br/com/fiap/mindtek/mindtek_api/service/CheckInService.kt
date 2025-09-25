package br.com.fiap.mindtek.mindtek_api.service

import br.com.fiap.mindtek.mindtek_api.dto.CheckInResponse
import br.com.fiap.mindtek.mindtek_api.dto.CreateCheckInRequest
import br.com.fiap.mindtek.mindtek_api.entity.CheckIn
import br.com.fiap.mindtek.mindtek_api.repository.CheckInRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CheckInService(private val repository: CheckInRepository) {

    fun create(userId: String, request: CreateCheckInRequest): CheckInResponse {
        val checkIn = CheckIn(
            userId = userId,
            moodScore = request.moodScore,
            emoji = request.emoji,
            comment = request.comment,
            extraAnswers = request.extraAnswers
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