package com.bremenband.shadoweng.domain.report.mapper

import com.bremenband.shadoweng.domain.report.dto.ReportResponse
import com.bremenband.shadoweng.domain.report.entity.Report

object ReportMapper {
    fun toResponse(report: Report) = ReportResponse(
        reportId = report.id,
        sessionId = report.session.id,
        totalScore = report.totalScore,
        wordAccuracy = report.wordAccuracy,
        prosodyAndStress = report.prosodyAndStress,
        wordRhythmScore = report.wordRhythmScore,
        boundaryToneScore = report.boundaryToneScore,
        dynamicStressScore = report.dynamicStressScore,
        speedSimilarity = report.speedSimilarity,
        pauseSimilarity = report.pauseSimilarity
    )
}