package jp.ac.it_college.std.s21020.news_manager.presentation.controller

import RegisterNewsRequest
import jp.ac.it_college.std.s21020.news_manager.application.security.NewsWithCategoryUserDetails
import jp.ac.it_college.std.s21020.news_manager.application.service.AdminNewsService
import jp.ac.it_college.std.s21020.news_manager.domain.model.NewsModel
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("admin/news")
@CrossOrigin(origins = ["http://localhost8081/"], allowCredentials = "true")
class AdminNewsController(
    private val adminNewsService: AdminNewsService
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterNewsRequest, authentication: Authentication) {
        val data = authentication.principal as NewsWithCategoryUserDetails
        adminNewsService.register(
            NewsModel(0, request.title, request.categoryId, request.publishAt, LocalDateTime.now(), data.id, request.body)
        )
    }
}