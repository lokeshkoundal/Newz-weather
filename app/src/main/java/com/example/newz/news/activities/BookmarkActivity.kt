package com.example.newz.news.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newz.R
import com.example.newz.databinding.ActivityBookmarkBinding
import com.example.newz.news.adapter.BookmarkAdapter
import com.example.newz.news.viewmodels.NewsVmDb
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: NewsVmDb by viewModels()

    private lateinit var binding :ActivityBookmarkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel = ViewModelProvider(this)[NewsVmDb::class.java]

        binding = DataBindingUtil.setContentView(this,R.layout.activity_bookmark)
        binding.bookMarkTitle = getString(R.string.bookmarked_news)
        binding.noBookmarkedNews = getString(R.string.no_bookmarked_news_found)

        viewModel.getAllBookmarkedNews()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.allNews.observe(this){
            if(it.isEmpty()){
                recyclerView.visibility = View.GONE
                binding.imageView.visibility = View.VISIBLE
                binding.textView.visibility = View.VISIBLE
            }
            else{
                recyclerView.visibility = View.VISIBLE
                val adapter = BookmarkAdapter(it, this,viewModel)
                recyclerView.adapter = adapter
                binding.imageView.visibility = View.GONE
                binding.textView.visibility = View.GONE
            }

        }

    }
}