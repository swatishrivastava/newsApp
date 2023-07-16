package com.android.newsapp.saved.repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_info")
data class News(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "author")
    val author: String = "",
    @ColumnInfo(name = "pic")
    val pic: String = "",
    @ColumnInfo(name = "url")
    val url: String = ""
)
