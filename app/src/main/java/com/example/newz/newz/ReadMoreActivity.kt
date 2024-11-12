package com.example.newz.newz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newz.R

class ReadMoreActivity : AppCompatActivity() {
    private lateinit var titleTv:TextView
    private lateinit var contentTv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_more)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        titleTv = findViewById(R.id.titleTv)
        contentTv = findViewById(R.id.contentTv)

        titleTv.text = title
        contentTv.text = content

    }
}