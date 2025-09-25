package br.com.fiap.mindtek.mindtekapi.controller

import br.com.fiap.mindtek.mindtekapi.dto.CreateCheckInRequest
import br.com.fiap.mindtek.mindtekapi.service.CheckInService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/check-ins")
class CheckInController(private val service: CheckInService) {

    @PostMapping
    fun createCheckIn(
        @RequestHeader("X-User-ID") userId: String, // Cabeçalho para identificar o usuário anonimamente
        @RequestBody request: CreateCheckInRequest
    ): ResponseEntity<Any> {
        if (userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("O cabeçalho 'X-User-ID' é obrigatório.")
        }
        val response = service.create(userId, request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping
    fun getHistory(
        @RequestHeader("X-User-ID") userId: String
    ): ResponseEntity<Any> {
        if (userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("O cabeçalho 'X-User-ID' é obrigatório.")
        }
        val history = service.findHistoryByUserId(userId)
        return ResponseEntity.ok(history)
    }
}