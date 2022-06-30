package jp.ac.it_college.std.s21020.news_manager.database.repository


import jp.ac.it_college.std.s21020.news_manager.database.mapper.*
import jp.ac.it_college.std.s21020.news_manager.database.record.News
import jp.ac.it_college.std.s21020.news_manager.database.record.NewsWithCategoryRecord
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsModel
import jp.ac.it_college.std.s21020.news_manager.domain.repository.NewsRepository
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsWithCategoryModel
import org.springframework.stereotype.Repository

import java.time.LocalDateTime

@Repository
class NewsRepositoryImpl(
    private val newsWithCategoryMapper: NewsWithCategoryMapper,
    private val newsMapper: NewsMapper
) : NewsRepository {
    override fun findAllWithCategory(): List<NewsWithCategoryModel> {
        return newsWithCategoryMapper.select {  }.map { toModel(it) }
    }

    override fun findWithCategory(id: Long): NewsWithCategoryModel? {
        return newsWithCategoryMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    override fun register(news: NewsModel) {
        newsMapper.insert(toRecord(news))
    }

    override fun update(
        id: Long,
        title: String?,
        categoryId: Long,
        publisherAt: LocalDateTime,
        createAt: LocalDateTime,
        userId: Long,
        body: String
    ) {
        newsMapper.updateByPrimaryKeySelective(News(id, title, categoryId, publisherAt, createAt, userId, body))
    }

    override fun delete(id: Long) {
        newsMapper.deleteByPrimaryKey(id)
    }

    private fun toRecord(model: NewsModel): News {
        return News(model.id, model.title, model.categoryId, model.publishAt, model.createAt, model.userId, model.body)
    }

    private fun toModel(record: NewsWithCategoryRecord): NewsWithCategoryModel {
        val news = NewsWithCategoryModel(
            record.id!!,
            record.title!!,
            record.body!!,
            record.categoryId!!,
            record.userId!!,
            record.createAt!!,
            record.publishAt!!
        )
        return news
    }
}