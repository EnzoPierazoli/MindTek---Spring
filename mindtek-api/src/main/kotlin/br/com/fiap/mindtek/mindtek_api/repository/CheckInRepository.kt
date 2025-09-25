package br.com.fiap.mindtek.mindtekapi.repository

import br.com.fiap.mindtek.mindtekapi.entity.CheckIn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CheckInRepository : JpaRepository<CheckIn, UUID> {
    // Spring Data JPA cria os métodos básicos (save, findById, etc.) automaticamente.
    // Podemos adicionar consultas customizadas aqui no futuro, como buscar por userId.
    fun findByUserIdOrderByCreatedAtDesc(userId: String): List<CheckIn>
}