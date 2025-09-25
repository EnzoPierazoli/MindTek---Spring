package br.com.fiap.mindtek.mindtek_api.service

import br.com.fiap.mindtek.mindtek_api.dto.PeriodDTO
import br.com.fiap.mindtek.mindtek_api.dto.PsychosocialReportDTO
import br.com.fiap.mindtek.mindtek_api.dto.RiskAnalysisDTO
import br.com.fiap.mindtek.mindtek_api.dto.StressorDTO
import br.com.fiap.mindtek.mindtek_api.repository.CheckInRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReportService(private val checkInRepository: CheckInRepository) {

    fun generatePsychosocialReport(startDate: LocalDate, endDate: LocalDate): PsychosocialReportDTO {
        val checkIns = checkInRepository.findByCreatedAtBetween(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay())

        if (checkIns.isEmpty()) {
            return PsychosocialReportDTO(
                reportPeriod = PeriodDTO(startDate, endDate),
                totalResponses = 0,
                averageMoodScore = 0.0,
                riskAnalysis = RiskAnalysisDTO(0.0, 0.0, 0.0),
                commonStressors = emptyList()
            )
        }

        val averageMood = checkIns.map { it.moodScore }.average()
        val stressorCounts = mutableMapOf<String, Int>()

        // LÓGICA CORRIGIDA: Acessamos o mapa diretamente
        checkIns.forEach { checkIn ->
            checkIn.extraAnswers.forEach { (question, answer) ->
                if (answer.equals("Sim", ignoreCase = true)) {
                    stressorCounts[question] = stressorCounts.getOrDefault(question, 0) + 1
                }
            }
        }

        var highRiskCount = 0
        var moderateRiskCount = 0

        checkIns.forEach { checkIn ->
            val yesCount = checkIn.extraAnswers.values.count { it.equals("Sim", ignoreCase = true) }
            if (yesCount >= 5) {
                highRiskCount++
            } else if (yesCount >= 3) {
                moderateRiskCount++
            }
        }

        val total = checkIns.size.toDouble()
        val highRiskPercentage = (highRiskCount / total) * 100
        val moderateRiskPercentage = (moderateRiskCount / total) * 100
        val lowRiskPercentage = 100 - highRiskPercentage - moderateRiskPercentage

        val commonStressors = stressorCounts.map { (factor, count) ->
            StressorDTO(
                factor = factor,
                count = count,
                percentage = (count / total) * 100
            )
        }.sortedByDescending { it.count }

        return PsychosocialReportDTO(
            reportPeriod = PeriodDTO(startDate, endDate),
            totalResponses = checkIns.size,
            averageMoodScore = averageMood,
            riskAnalysis = RiskAnalysisDTO(highRiskPercentage, moderateRiskPercentage, lowRiskPercentage),
            commonStressors = commonStressors
        )
    }
}