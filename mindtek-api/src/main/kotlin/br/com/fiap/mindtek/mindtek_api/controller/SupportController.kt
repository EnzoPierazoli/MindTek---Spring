package br.com.fiap.mindtek.mindtek_api.controller

import br.com.fiap.mindtek.mindtek_api.dto.SupportChannelDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/support/channels")
class SupportController {

    @GetMapping
    fun getSupportChannels(): ResponseEntity<List<SupportChannelDTO>> {
        // Por enquanto, vamos retornar uma lista fixa.
        // No futuro, isso poderia vir de um banco de dados.
        val channels = listOf(
            SupportChannelDTO(
                title = "Apoio Psicológico Interno",
                description = "Agende uma conversa confidencial com nossa equipe de psicologia.",
                contact = "psico@empresa.com.br",
                link = null,
                type = "email"
            ),
            SupportChannelDTO(
                title = "Canal de Denúncias",
                description = "Relate qualquer situação de forma anônima.",
                contact = null,
                link = "https://canaldenuncias.com.br/empresa",
                type = "link"
            ),
            SupportChannelDTO(
                title = "Centro de Valorização da Vida (CVV)",
                description = "Converse com um voluntário do CVV de forma sigilosa e gratuita.",
                contact = "188",
                link = "https://www.cvv.org.br/",
                type = "phone"
            )
        )

        return ResponseEntity.ok(channels)
    }
}