package com.apps.newsapp.news

import com.apps.newsapp.data.model.ArticlesItem

interface OnClickListener {

    fun onClick(articlesItem: ArticlesItem)
}