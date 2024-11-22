package com.example.newz.newz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newz.R
import com.example.newz.db.NewsVmDb
import com.example.newz.newz.adapter.BookmarkAdapter
import com.example.newz.newz.adapter.NewsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)


        val viewModel = ViewModelProvider(this)[NewsVmDb::class.java]
        viewModel.getAllBookmarkedNews()


        viewModel.allNews.observe(this){
            recyclerView = findViewById(R.id.recyclerView)
            val adapter = BookmarkAdapter(it, this,viewModel)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }





    }
}