package jp.ac.it_college.std.s21020.news_manager.application.service
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsModel
import jp.ac.it_college.std.s21020.news_manager.domain.repository.NewsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNewsService (
    private val newsRepository: NewsRepository
){
    @Transactional
    fun register(news: NewsModel) {
        newsRepository.findWithCategory(news.id)?.let {
            throw IllegalArgumentException("すでに存在するID: ${news.id}")
        }
        newsRepository.register(news)
    }
}