package com.app.asad.alphanews.repository

import com.app.asad.alphanews.api.RetrofitInstance
import com.app.asad.alphanews.db.ArticleDatabase
import com.app.asad.alphanews.models.Article

class NewsRepository(val db:ArticleDatabase) {
    suspend fun getHeadlines(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getTopHeadlines(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber: Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article)=db.getArticleDao().upsert(article)

    suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)
    fun getFavoriteNews()=db.getArticleDao().getAllArticles()
}