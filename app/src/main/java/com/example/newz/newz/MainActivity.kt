package com.example.newz.newz

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newz.R
import com.example.newz.db.NewsVMFactory
import com.example.newz.db.NewsVmDb
import com.example.newz.newz.adapter.NewsAdapter
import com.example.newz.newz.models.NewsModel
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookmarkBtn: FloatingActionButton
    private lateinit var chipGroup: ChipGroup
    private  var newsData: NewsModel? = null
    private lateinit var loader: ProgressBar
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var  viewModelRoom :NewsVmDb
//    private lateinit var vm : NewsVM
    private val vm: NewsVM by viewModels()



    val hashmap = HashMap<String, Boolean>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter: NewsAdapter?
        loader = findViewById(R.id.loader)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)
        bookmarkBtn = findViewById(R.id.bookMarkBtn)

//        vm = NewsVM()
        viewModelRoom = ViewModelProvider(this, NewsVMFactory(application))[NewsVmDb::class.java]

        vm.currentCategory.observe(this){
            lifecycleScope.launch {
                vm.getTopHeadlines("us")
            }
        }

        if (isInternetAvailable(this)) {
           vm.currentCategory.value = "general"
        } else {
            showNoInternetDialog(this)
        }

        viewModelRoom.getAllBookmarkedNews()

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
            if (!isInternetAvailable(this)) {
                showNoInternetDialog(this)
                refreshLayout.isRefreshing = false

            } else {
                lifecycleScope.launch {
                    vm.getTopHeadlines("us")
                    refreshLayout.isRefreshing = false
                }
            }

        }

        recyclerView = findViewById(R.id.recyclerView)



        adapter = NewsAdapter(vm.articles, this,viewModelRoom)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        chipGroup = findViewById(R.id.chipGroup)
        chipGroup.getChildAt(0).performClick()

        vm.articles.observe(this) {
            newsData = it
            adapter.updateData(it)
            adapter.notifyDataSetChanged()

        }

        chipGroup.getChildAt(0).setOnClickListener {
            if (vm.currentCategory.value != "general") {

                if (!isInternetAvailable(this)) {
                    it.isSelected = false
                    showNoInternetDialog(this)
                } else {
                    vm.currentCategory.value = "general"
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }
            }
            else{
                recyclerView.scrollToPosition(0)

            }
        }

        chipGroup.getChildAt(1).setOnClickListener {
            if (vm.currentCategory.value != "business") {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    vm.currentCategory.value = "business"
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }
            }
            else{
                recyclerView.scrollToPosition(0)

            }

            }
        chipGroup.getChildAt(2).setOnClickListener {
        if (vm.currentCategory.value != "entertainment") {

            if (!isInternetAvailable(this)) {
                showNoInternetDialog(this)
            } else {
                vm.currentCategory.value = "entertainment"
                adapter.notifyDataSetChanged()
                recyclerView.scrollToPosition(0)
            }
        }
        else{
            recyclerView.scrollToPosition(0)

        }

        }
        chipGroup.getChildAt(3).setOnClickListener {
            if (vm.currentCategory.value != "health") {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    vm.currentCategory.value = "health"
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }

            }
            else{
                recyclerView.scrollToPosition(0)

            }
        }
        chipGroup.getChildAt(4).setOnClickListener {
            if (vm.currentCategory.value != "science") {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    vm.currentCategory.value = "science"
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }

            }
            else{
                recyclerView.scrollToPosition(0)

            }
        }
        chipGroup.getChildAt(5).setOnClickListener {
            if (vm.currentCategory.value != "sports") {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    vm.currentCategory.value = "sports"
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(0)
                }

            }
            else{
                recyclerView.scrollToPosition(0)

            }
        }

        bookmarkBtn.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }



//        viewModelRoom.allNews.observe(this){
//
//            if(it!=null&&newsData!=null){
//                if(viewModelRoom.allNews.isInitialized&& newsData!=null){
//                    newsData?.articles?.forEach {it1->
//                        hashmap[it1.title] = false
//                    }
//
//                    viewModelRoom.allNews.observe(this){
//                        viewModelRoom.getAllBookmarkedNews()
//                        viewModelRoom.allNews.value?.forEach {it3->
//                            hashmap[it3.title] = true
//                        }
//                    }
//
//                    adapter.updateHashmap(hashmap)
//                }
//            }
//
//
//        }

    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    private fun showNoInternetDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("No Internet Connection")
        builder.setMessage("Please check your internet connection and try again.")
        builder.setCancelable(true)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss() // Close the dialog when the user presses OK
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            vm.getTopHeadlines("us")
        }
    }
}
