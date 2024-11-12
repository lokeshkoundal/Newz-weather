package com.example.newz.newz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newz.R
import com.example.newz.newz.adapter.NewsAdapter
import com.example.newz.newz.models.NewsModel
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var nextBtn: Button
    private lateinit var chipGroup: ChipGroup
    private lateinit var newsData: NewsModel
    private lateinit var loader: ProgressBar
    private lateinit var refreshLayout: SwipeRefreshLayout
    private var category = "general"


    val vm = NewsVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter: NewsAdapter?
        loader = findViewById(R.id.loader)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)

        lifecycleScope.launch {
            vm.getTopHeadlines("us", category)
        }

        vm.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                loader.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                loader.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        refreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                vm.getTopHeadlines("us", category)
                refreshLayout.isRefreshing = false
            }
        }

        //  nextBtn = findViewById(R.id.nextBtn)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = NewsAdapter(vm.articles, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        chipGroup = findViewById(R.id.chipGroup)
        chipGroup.getChildAt(0).performClick()

        chipGroup.getChildAt(0).setOnClickListener {
            if(category != "general") {
                category = "general"
                lifecycleScope.launch {
                    vm.getTopHeadlines("us", category)
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }
            }
        }
        chipGroup.getChildAt(1).setOnClickListener {
            if(category != "business") {
                category = "business"
                lifecycleScope.launch {
                    vm.getTopHeadlines("us", category)
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)

                }
            }

        }
        chipGroup.getChildAt(2).setOnClickListener {
            if(category != "entertainment") {
                category = "entertainment"
                lifecycleScope.launch {
                    vm.getTopHeadlines("us", category)
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)

                }
            }

        }
        chipGroup.getChildAt(3).setOnClickListener {
            if(category != "health") {
                category = "health"
                lifecycleScope.launch {
                    vm.getTopHeadlines("us", category)
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)

                }
            }
        }
        chipGroup.getChildAt(4).setOnClickListener {
            if(category != "science") {
                category = "science"
                lifecycleScope.launch {
                    vm.getTopHeadlines("us", category)
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }
            }
        }
        chipGroup.getChildAt(5).setOnClickListener {
            if(category != "sports") {

                category = "sports"
                lifecycleScope.launch {
                    vm.getTopHeadlines("us", category)
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)

                }
            }



//        nextBtn.setOnClickListener{
//           val intent = Intent(this, MainNotesActivity::class.java)
//            startActivity(intent)
//        }

            vm.articles.observe(this) {
                newsData = it
                adapter.updateData(it)
                adapter.notifyDataSetChanged()

            }

        }

    }
}
