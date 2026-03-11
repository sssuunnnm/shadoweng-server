package com.bremenband.shadoweng.global.config

import com.bremenband.shadoweng.domain.bookmark.entity.Bookmark
import com.bremenband.shadoweng.domain.bookmark.repository.BookmarkRepository
import com.bremenband.shadoweng.domain.report.entity.Report
import com.bremenband.shadoweng.domain.report.repository.ReportRepository
import com.bremenband.shadoweng.domain.study.entity.Sentence
import com.bremenband.shadoweng.domain.study.entity.StudySession
import com.bremenband.shadoweng.domain.study.repository.SentenceRepository
import com.bremenband.shadoweng.domain.study.repository.StudySessionRepository
import com.bremenband.shadoweng.domain.user.entity.User
import com.bremenband.shadoweng.domain.user.repository.UserRepository
import com.bremenband.shadoweng.domain.video.entity.Video
import com.bremenband.shadoweng.domain.video.repository.VideoRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Component
class DataInitializer(
    private val videoRepository: VideoRepository,
    private val userRepository: UserRepository,
    private val studySessionRepository: StudySessionRepository,
    private val sentenceRepository: SentenceRepository,
    private val reportRepository: ReportRepository,
    private val bookmarkRepository: BookmarkRepository
) : ApplicationRunner {

    @Transactional
    override fun run(args: ApplicationArguments) {
        // 1. Video
        val video = videoRepository.findByEmbedUrl("https://www.youtube.com/embed/dQw4w9WgXcQ")
            .orElseGet {
                videoRepository.save(
                    Video(
                        videoId = "dQw4w9WgXcQ",
                        title = "Rick Astley - Never Gonna Give You Up",
                        embedUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ",
                        thumbnailUrl = "https://img.youtube.com/vi/dQw4w9WgXcQ/0.jpg",
                        duration = 213,
                        channelTitle = "Rick Astley"
                    )
                )
            }

        // 2. User
        val user = userRepository.findAll().firstOrNull()
            ?: userRepository.save(
                User(
                    email = "test@shadoweng.com",
                    nickname = "TestUser",
                    provider = "GUEST",
                    providerId = "test-init"
                )
            )

        // 3. StudySession
        val session = studySessionRepository.findAll().firstOrNull()
            ?: studySessionRepository.save(
                StudySession(
                    user = user,
                    video = video,
                    startSec = 6721.56,
                    endSec = 8707.29
                )
            )

        // 4. Sentences
        if (sentenceRepository.findAllBySessionId(session.id).isEmpty()) {
            listOf(
                Sentence(session = session, content = "I had this meeting with a big studio Hollywood casting director.", startSec = 3, endSec = 7, durationSec = 4),
                Sentence(session = session, content = "She told me I was very talented.", startSec = 8, endSec = 11, durationSec = 3),
                Sentence(session = session, content = "But I needed to change my name.", startSec = 12, endSec = 15, durationSec = 3),
            ).forEach { sentenceRepository.save(it) }
        }

        // 5. Report
        if (reportRepository.findBySessionId(session.id).isEmpty) {
            reportRepository.save(
                Report(
                    session = session,
                    totalScore = BigDecimal("85.00"),
                    wordAccuracy = BigDecimal("90.00"),
                    prosodyAndStress = BigDecimal("80.00"),
                    wordRhythmScore = BigDecimal("85.00"),
                    boundaryToneScore = BigDecimal("82.00"),
                    dynamicStressScore = BigDecimal("88.00"),
                    speedSimilarity = BigDecimal("83.00"),
                    pauseSimilarity = BigDecimal("87.00")
                )
            )
        }

        // 6. Bookmark
        val sentence = sentenceRepository.findAll().firstOrNull() ?: return
        if (bookmarkRepository.findByUserIdAndSentenceId(user.id, sentence.id) == null) {
            bookmarkRepository.save(Bookmark(user = user, sentence = sentence))
        }
    }
}