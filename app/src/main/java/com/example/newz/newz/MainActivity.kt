package com.example.newz.newz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newz.R
import com.example.newz.newz.adapter.NewsAdapter
import com.example.newz.newz.models.NewsModel
import com.example.newz.room.MainNotesActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var nextBtn : Button

    private lateinit var newsData : NewsModel

    val vm = NewsVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter : NewsAdapter? = null

        lifecycleScope.launch {
            vm.getTopHeadlines("us","business")
        }
        nextBtn = findViewById(R.id.nextBtn)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = NewsAdapter(vm.articles,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



        nextBtn.setOnClickListener{
           val intent = Intent(this, MainNotesActivity::class.java)
            startActivity(intent)
        }

        vm.articles.observe(this) {
            newsData = it
            adapter.updateData(it)
        }

//        lifecycleScope.launch {
//            vm.articles.observe(MainActivity()){
//
//            }
    }

}
