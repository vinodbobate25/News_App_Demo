package com.apps.newsapp.data.api

import com.apps.newsapp.data.model.ApiNews

class ApiHelperImpl (private  val newsApiService: NewsApiService): ApiHelper {
    override suspend fun getNews(): ApiNews =newsApiService.getApiNews()


}