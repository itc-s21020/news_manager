package jp.ac.it_college.std.s21020.news_manager.database.record

import java.sql.Timestamp
import java.time.LocalDateTime


data class NewsWithCategoryRecord(
    val id: Long? = null,
    val title: String? = null,
    val body: String? = null,
    val categoryId: Long? = null,
    val userId: Long? = null,
    val createAt: LocalDateTime? = null,
    val publishAt: LocalDateTime? = null,
)
