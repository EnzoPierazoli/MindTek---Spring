package br.com.fiap.mindtek.mindtekapi.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "check_ins")
class CheckIn(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    // ID anônimo do usuário que fez o check-in
    @Column(nullable = false)
    val userId: String,

    @Column(nullable = false)
    val moodScore: Int,

    val emoji: String,

    @Column(length = 500) // Define um tamanho máximo para o comentário
    val comment: String?,

    // Armazena as respostas extras como um JSON ou texto simples
    @Column(length = 1000)
    val extraAnswers: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)