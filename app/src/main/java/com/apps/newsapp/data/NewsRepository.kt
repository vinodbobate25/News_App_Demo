package com.apps.newsapp.data

import com.apps.newsapp.data.api.ApiHelper

    class NewsRepository (private  val apiHelper: ApiHelper) {
    suspend fun getNews()=apiHelper.getNews()
}