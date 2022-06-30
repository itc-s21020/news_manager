package jp.ac.it_college.std.s21020.news_manager.domain.repository

import jp.ac.it_college.std.s21020.news_manager.database.record.News
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsModel
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsWithCategoryModel
import java.time.LocalDateTime

interface NewsRepository {
    fun findAllWithCategory(): List<NewsWithCategoryModel>

    fun findWithCategory(id: Long): NewsWithCategoryModel?

    fun register(news: NewsModel)

    fun update(id: Long, title: String?, categoryId: Long, publisherAt: LocalDateTime, createAt: LocalDateTime, userId: Long, body: String)

    fun delete(id: Long)
}