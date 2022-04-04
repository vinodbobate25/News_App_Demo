package com.apps.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.newsapp.data.model.ArticlesItem
import com.apps.newsapp.news.APINewsApdater
import com.apps.newsapp.news.ListViewModel
import com.apps.newsapp.news.OnClickListener
import com.apps.newsapp.utils.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.activity_main.toolbar
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity:AppCompatActivity(),OnClickListener {
    private val  listViewModel: ListViewModel by viewModel()
    private lateinit var adapter: APINewsApdater
    private  var url:String?="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        url=intent.getStringExtra("URL")
        url?.let { webview.loadUrl(it) }
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

        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter= APINewsApdater(arrayListOf(),this)
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
                    recyclerView.visibility= View.VISIBLE
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
    }
}