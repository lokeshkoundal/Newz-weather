package com.example.newz.newz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newz.R
import com.example.newz.newz.adapter.NewsAdapter

class BookmarkActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)


//        recyclerView = findViewById(R.id.recyclerView)
//        val adapter = NewsAdapter()
//        recyclerView.adapter = adapter


    }
}