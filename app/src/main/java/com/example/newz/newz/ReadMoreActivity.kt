package com.example.newz.newz

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newz.R

class ReadMoreActivity : AppCompatActivity() {
    private lateinit var titleTv:TextView
    private lateinit var contentTv:TextView
    private lateinit var authorTv:TextView
    private lateinit var publishedAtTv:TextView
    private lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_more)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val author = intent.getStringExtra("author")
        val publishedAt = intent.getStringExtra("publishedAt")
        val imageUrl = intent.getStringExtra("image")


        titleTv = findViewById(R.id.titleTv)
        contentTv = findViewById(R.id.contentTv)
        authorTv = findViewById(R.id.authorTv)
        publishedAtTv = findViewById(R.id.dateTv)
        imageView = findViewById(R.id.imageView)

        titleTv.text = title
        contentTv.text = content
        authorTv.text = "~" + author
        publishedAtTv.text = publishedAt

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
            .error(R.drawable.ic_error) // Optional: add an error image in case of failure
            .centerCrop()
            .into(imageView)





    }
}