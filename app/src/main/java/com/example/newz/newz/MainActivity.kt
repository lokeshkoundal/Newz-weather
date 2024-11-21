package com.example.newz.newz

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newz.R
import com.example.newz.db.NewsVmDb
import com.example.newz.newz.adapter.NewsAdapter
import com.example.newz.newz.models.NewsModel
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookmarkBtn: FloatingActionButton
    private lateinit var chipGroup: ChipGroup
    private lateinit var newsData: NewsModel
    private lateinit var loader: ProgressBar
    private lateinit var refreshLayout: SwipeRefreshLayout
    private var category = "general"


    val vm = NewsVM()
    val viewModel = ViewModelProvider(this)[NewsVmDb::class.java]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter: NewsAdapter?
        loader = findViewById(R.id.loader)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)
        bookmarkBtn = findViewById(R.id.bookMarkBtn)

        if (isInternetAvailable(this)) {
            lifecycleScope.launch {
                vm.getTopHeadlines("us", category)
            }
        } else {
            showNoInternetDialog(this)
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
            if (!isInternetAvailable(this)) {
                showNoInternetDialog(this)
                refreshLayout.isRefreshing = false

            } else {
                lifecycleScope.launch {
                    vm.getTopHeadlines("us", category)
                    refreshLayout.isRefreshing = false
                }
            }

        }

        //  nextBtn = findViewById(R.id.nextBtn)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = NewsAdapter(vm.articles, this,viewModel)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        chipGroup = findViewById(R.id.chipGroup)
        chipGroup.getChildAt(0).performClick()

        chipGroup.getChildAt(0).setOnClickListener {
            if (category != "general") {
                category = "general"
                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {

                    lifecycleScope.launch {
                        vm.getTopHeadlines("us", category)
                        adapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(0)
                    }
                }

            }
        }
        chipGroup.getChildAt(1).setOnClickListener {
            if (category != "business") {
                category = "business"
                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    lifecycleScope.launch {
                        vm.getTopHeadlines("us", category)
                        adapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(0)

                    }
                }

            }

        }
        chipGroup.getChildAt(2).setOnClickListener {
            if (category != "entertainment") {
                category = "entertainment"
                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    lifecycleScope.launch {
                        vm.getTopHeadlines("us", category)
                        adapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(0)

                    }
                }

            }

        }
        chipGroup.getChildAt(3).setOnClickListener {
            if (category != "health") {
                category = "health"
                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    lifecycleScope.launch {
                        vm.getTopHeadlines("us", category)
                        adapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(0)

                    }
                }

            }
        }
        chipGroup.getChildAt(4).setOnClickListener {
            if (category != "science") {
                category = "science"
                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    lifecycleScope.launch {
                        vm.getTopHeadlines("us", category)
                        adapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(0)
                    }
                }

            }
        }
        chipGroup.getChildAt(5).setOnClickListener {
            if (category != "sports") {
                category = "sports"

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    lifecycleScope.launch {
                        vm.getTopHeadlines("us", category)
                        adapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(0)
                    }
                }

            }
        }

        bookmarkBtn.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }

        vm.articles.observe(this) {
            newsData = it
            adapter.updateData(it)
            adapter.notifyDataSetChanged()

        }
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
        builder.setCancelable(false) // Prevent the dialog from being dismissed by clicking outside
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss() // Close the dialog when the user presses OK
        }
        val dialog = builder.create()
        dialog.show()
    }
}
