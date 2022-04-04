package com.apps.newsapp.di

import com.apps.newsapp.news.ListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewmodelModule= module {
    viewModel{
        ListViewModel(get())
    }
}