package br.com.fiap.mindtek.mindtek_api.dto

import java.time.LocalDate

// DTO principal que será retornado pela API
data class PsychosocialReportDTO(
    val reportPeriod: PeriodDTO,
    val totalResponses: Int,
    val averageMoodScore: Double,
    val riskAnalysis: RiskAnalysisDTO,
    val commonStressors: List<StressorDTO>
)

// DTOs auxiliares para organizar o relatório
data class PeriodDTO(
    val start: LocalDate,
    val end: LocalDate
)

data class RiskAnalysisDTO(
    val highRiskPercentage: Double,
    val moderateRiskPercentage: Double,
    val lowRiskPercentage: Double
)

data class StressorDTO(
    val factor: String,
    val percentage: Double,
    val count: Int
)