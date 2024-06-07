package com.app.asad.alphanews.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.app.asad.alphanews.models.Article

import androidx.room.Query


@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long


    @Delete
    suspend fun deleteArticle(article: Article)

}