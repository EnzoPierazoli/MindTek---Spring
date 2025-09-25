package br.com.fiap.mindtek.mindtek_api.repository

import br.com.fiap.mindtek.mindtek_api.entity.CheckIn
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CheckInRepository : MongoRepository<CheckIn, String> {

    fun findByUserIdOrderByCreatedAtDesc(userId: String): List<CheckIn>

    fun findByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): List<CheckIn>
}