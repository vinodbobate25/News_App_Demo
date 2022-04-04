package com.apps.newsapp.di

import com.apps.newsapp.data.NewsRepository
import org.koin.dsl.module.module

val repoModule = module {
    single {
        NewsRepository(get())
    }
}