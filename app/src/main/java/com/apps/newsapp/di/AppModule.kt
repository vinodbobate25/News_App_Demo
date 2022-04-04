package com.apps.newsapp.di


import com.apps.newsapp.data.api.ApiHelper
import com.apps.newsapp.data.api.ApiHelperImpl
import com.apps.newsapp.data.api.NewsApiService
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule= module {
    single { provideRetrofit() }
    single {  provideApiService(get())}
    single<ApiHelper> {return@single ApiHelperImpl(get())  }
}


    private val BASE_URL:String="https://newsapi.org/v2/"



    fun provideRetrofit(): Retrofit {
       return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun provideApiService(retrofit: Retrofit) = retrofit.create(NewsApiService::class.java)!!


    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper



