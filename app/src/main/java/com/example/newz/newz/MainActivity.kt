package com.example.newz.newz

import android.content.Intent
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
import com.example.newz.room.MainNotesActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var nextBtn : Button
    private lateinit var chipGroup:ChipGroup
    private lateinit var newsData : NewsModel
    private lateinit var loader : ProgressBar
    private lateinit var refreshLayout : SwipeRefreshLayout


    val vm = NewsVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter : NewsAdapter?
        loader = findViewById(R.id.loader)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)

        lifecycleScope.launch {
            vm.getTopHeadlines("us")
        }

        vm.isLoading.observe(this){
         isLoading ->
            if(isLoading){
                loader.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            else{
                loader.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE


            }
        }

        refreshLayout.setOnRefreshListener{
            lifecycleScope.launch {
                vm.getTopHeadlines("us")
                refreshLayout.isRefreshing = false
            }
        }

      //  nextBtn = findViewById(R.id.nextBtn)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = NewsAdapter(vm.articles,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        chipGroup = findViewById(R.id.chipGroup)
//        chipGroup.getChildAt(0).performClick()
        chipGroup.getChildAt(0).performClick()

        chipGroup.setOnClickListener{

        }
//        chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
//            for (i in 0 until group.childCount) {
//                val chip = group.getChildAt(i) as Chip
//                if(chip.isSelected){
//                    chip.chipStrokeColor = getColorStateList(R.color.blue)
//                }
//                else
//                    chip.chipStrokeColor = getColorStateList(R.color.black)
//            }
//        }


//        nextBtn.setOnClickListener{
//           val intent = Intent(this, MainNotesActivity::class.java)
//            startActivity(intent)
//        }

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
