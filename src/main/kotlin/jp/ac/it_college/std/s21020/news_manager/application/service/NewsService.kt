package jp.ac.it_college.std.s21020.news_manager.application.service

import jp.ac.it_college.std.s21020.news_manager.database.record.News
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsModel
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsWithCategoryModel
import jp.ac.it_college.std.s21020.news_manager.domain.repository.NewsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NewsService (
    private val newsRepository: NewsRepository
) {
    fun getList(): List<NewsWithCategoryModel> {
        return newsRepository.findAllWithCategory()
    }

    fun getDetail(newsId: Long): NewsWithCategoryModel {
        return newsRepository.findWithCategory(newsId) ?: throw IllegalArgumentException("存在しないNewsID: $newsId")
    }
    @Transactional
    fun register(news: NewsModel) {
        newsRepository.findWithCategory(news.id)?.let {
            throw IllegalArgumentException("すでに存在するID: ${news.id}")
        }
        newsRepository.register(news)
    }
}