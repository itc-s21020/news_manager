/*
 * Auto-generated file. Created by MyBatis Generator
 */
package jp.ac.it_college.std.s21020.news_manager.database.record

import java.util.Date

data class News(
    var id: Long? = null,
    var title: String? = null,
    var categoryId: Long? = null,
    var publishAt: Date? = null,
    var createAt: Date? = null,
    var userId: Long? = null,
    var body: String? = null
)