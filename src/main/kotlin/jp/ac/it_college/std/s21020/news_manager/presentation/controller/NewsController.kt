package jp.ac.it_college.std.s21020.news_manager.presentation.controller

import GetNewsDetailResponse
import GetNewsListResponse
import NewsInfo
import RegisterNewsRequest
import jp.ac.it_college.std.s21020.news_manager.application.security.NewsWithCategoryUserDetails
import jp.ac.it_college.std.s21020.news_manager.application.service.NewsService
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsModel
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("news")
@CrossOrigin
class NewsController(
    private val newsService: NewsService
) {
    @GetMapping("/list")
    fun getList(): GetNewsListResponse {
        val newsList = newsService.getList().map {
            NewsInfo(it)
        }
        return GetNewsListResponse(newsList)
    }

    @GetMapping("/detail/{news_id}")
    fun getDetail(@PathVariable("news_id") newsId: Long): GetNewsDetailResponse {
        val news = newsService.getDetail(newsId)
        return GetNewsDetailResponse(news)
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterNewsRequest, authentication: Authentication) {
        val data = authentication.principal as NewsWithCategoryUserDetails
        newsService.register(
            NewsModel(0, request.title, request.categoryId, request.publishAt, LocalDateTime.now(), data.id, request.body)
        )
    }

}