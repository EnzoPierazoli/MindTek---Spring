package br.com.fiap.mindtek.mindtek_api.controller

import br.com.fiap.mindtek.mindtek_api.service.ReportService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/v1/reports")
class ReportController(private val reportService: ReportService) {

    @GetMapping("/psychosocial-risks")
    fun getPsychosocialReport(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate
    ): ResponseEntity<Any> {

        if (startDate.isAfter(endDate)) {
            return ResponseEntity.badRequest().body("A data de início não pode ser posterior à data de fim.")
        }

        val report = reportService.generatePsychosocialReport(startDate, endDate)
        return ResponseEntity.ok(report)
    }
}