package com.apps.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.newsapp.data.model.ArticlesItem
import com.apps.newsapp.news.APINewsApdater
import com.apps.newsapp.news.ListViewModel
import com.apps.newsapp.news.OnClickListener
import com.apps.newsapp.utils.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listitem.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),OnClickListener {
    private val  listViewModel: ListViewModel by viewModel()
    private lateinit var adapter:APINewsApdater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        setupUI()
        setupObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private  fun setupUI()
    {
        var articlesItem=ArticlesItem(title = "In 2011, were Maharashtra andhashraddhra nirmulan samiti workers really arrested for naxal links?",description = "since the pune police crackdonwn on maoist,organiziations and naxal supporters a lot being discussed about the action taken against Naxalities during the UPA regime...");
        txtHeader.text=articlesItem.title
       txtNews.text=articlesItem.description
       tv_source.text="CNN"
        Glide.with(imageViewAvatar.context)
            .load("https://www.greatandhra.com/newphotos10/MishanImpossible21648806581.jpg")
            .into(imageViewAvatar)
        recyclerView.layoutManager=LinearLayoutManager(this)
       adapter=APINewsApdater(arrayListOf(),this)
     recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,(recyclerView.layoutManager as LinearLayoutManager).orientation))
        recyclerView.adapter=adapter
    }


    private  fun showNewsList( newList:List<ArticlesItem>)
    {
        adapter.notifyDataSetChanged()
       adapter.addData(newList)
    }

    private fun setupObserver()
    {
    listViewModel.getNews().observe(this, Observer {
        when(it.status)
        {
            Status.SUCCESS->
            {
            progressBar.visibility= View.GONE
                recyclerView.visibility=View.VISIBLE
                it.data?.let { it1 -> showNewsList(it1) }
            }
            Status.LOADING -> {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            Status.ERROR -> {
                //Handle Error
                progressBar.visibility = View.GONE
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
        })
    }

    override fun onClick(articlesItem: ArticlesItem) {
       Intent(this, DetailsActivity::class.java).also {
            it.putExtra("URL",articlesItem.url)
            startActivity(it)
        }
    }
}