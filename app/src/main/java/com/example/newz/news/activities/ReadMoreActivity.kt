package com.example.newz.news.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newz.R
import com.example.newz.databinding.ActivityReadMoreBinding

class ReadMoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val author = intent.getStringExtra("author")
        val publishedAt = intent.getStringExtra("publishedAt")
        val imageUrl = intent.getStringExtra("image")

        binding.titleTv.text = title
        binding.contentTv.text = content
        binding.authorTv.text = "~" + author
        binding.dateTv.text = publishedAt

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
            .error(R.drawable.ic_error) // Optional: add an error image in case of failure
            .centerCrop()
            .into(binding.imageView)

    }
}