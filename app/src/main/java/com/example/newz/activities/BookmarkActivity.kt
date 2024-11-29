package com.example.newz.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newz.R
import com.example.newz.viewmodels.NewsVmDb
import com.example.newz.adapter.BookmarkAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: NewsVmDb by viewModels()
    private lateinit var image : ImageView
    private lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)
        image = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)

//        val viewModel = ViewModelProvider(this)[NewsVmDb::class.java]
        viewModel.getAllBookmarkedNews()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.allNews.observe(this){
            if(it.isEmpty()){
               recyclerView.visibility = View.GONE
                image.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
            }
            else{
                recyclerView.visibility = View.VISIBLE
                val adapter = BookmarkAdapter(it, this,viewModel)
                recyclerView.adapter = adapter

                image.visibility = View.GONE
                textView.visibility = View.GONE
            }

        }

    }
}