package br.com.fiap.mindtek.mindtek_api.repository // CORRIGIDO

import br.com.fiap.mindtek.mindtek_api.entity.CheckIn // CORRIGIDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CheckInRepository : JpaRepository<CheckIn, UUID> {
    fun findByUserIdOrderByCreatedAtDesc(userId: String): List<CheckIn>
}