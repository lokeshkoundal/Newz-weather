package com.example.newz.news.activities

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
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newz.R
import com.example.newz.news.paging.NewzPagingSource
import com.example.newz.news.paging.PagerVM
import com.example.newz.news.paging.PagingAdapter
import com.example.newz.news.viewmodels.NewsVmDb
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val CATEGORY_GENERAL = "General"
private const val CATEGORY_BUSINESS = "business"
private const val CATEGORY_HEALTH = "health"
private const val CATEGORY_SCIENCE = "science"
private const val CATEGORY_SPORTS = "sports"
private const val CATEGORY_ENTERTAINMENT = "entertainment"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookmarkBtn: FloatingActionButton
    private lateinit var chipGroup: ChipGroup
//    private  var newsData: NewsModel? = null
    private lateinit var loader: ProgressBar
    private lateinit var refreshLayout: SwipeRefreshLayout

//    private  val vm: NewsVM by viewModels()
    private  val viewModelRoom: NewsVmDb by viewModels()
//    lateinit var adapter: NewsAdapter

    private lateinit var pagingAdapter : PagingAdapter
    private val pagingVM : PagerVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loader = findViewById(R.id.loader)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)
        bookmarkBtn = findViewById(R.id.bookMarkBtn)
        chipGroup = findViewById(R.id.chipGroup)


        recyclerView = findViewById(R.id.recyclerView)
        pagingAdapter = PagingAdapter(this,viewModelRoom)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = pagingAdapter

        chipGroup.getChildAt(0).performClick()


        refreshLayout.setOnRefreshListener {
            if (!isInternetAvailable(this)) {
                showNoInternetDialog(this)
                refreshLayout.isRefreshing = false

            } else {
                lifecycleScope.launch {


                    if(pagingAdapter.itemCount>0){
                        pagingAdapter.refresh()
                    }

                   else{
                       chipGroup.getChildAt(0).performClick()
                    }
                    refreshLayout.isRefreshing = false
                }
            }

        }


//        pagingVM.category.value = CATEGORY_GENERAL
//        pagingVM.getHeadLines()

        pagingVM.category.observe(this){
            if(isInternetAvailable(this)) {
//                pagingVM.getHeadLines()
                lifecycleScope.launch {
                    pagingVM.getHeadLines().collectLatest {
                        pagingAdapter.submitData(it)
                        recyclerView.scrollToPosition(0)
//                        pagingAdapter.notifyDataSetChanged()
                    }
                }
            }
            else{
                showNoInternetDialog(this)
            }
        }

        NewzPagingSource.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                loader.visibility = View.VISIBLE
            } else {
                loader.visibility = View.GONE
            }
        }

//        --------------------------------------------------------


        chipGroup.getChildAt(0).setOnClickListener {
            if (pagingVM.category.value != CATEGORY_GENERAL) {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)

                } else {
                    pagingVM.category.value = CATEGORY_GENERAL
                }
            }
            else{
                recyclerView.smoothScrollToPosition(0)
            }
        }

        chipGroup.getChildAt(1).setOnClickListener {
            if (pagingVM.category.value != CATEGORY_BUSINESS) {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    pagingVM.category.value = CATEGORY_BUSINESS
                }
            } else {
                recyclerView.smoothScrollToPosition(0)
            }
        }

        chipGroup.getChildAt(2).setOnClickListener {
        if (pagingVM.category.value != CATEGORY_ENTERTAINMENT) {

            if (!isInternetAvailable(this)) {
                showNoInternetDialog(this)
            } else {
                pagingVM.category.value = CATEGORY_ENTERTAINMENT
            }
        }
        else{
            recyclerView.smoothScrollToPosition(0)
        }
        }

        chipGroup.getChildAt(3).setOnClickListener {
            if (pagingVM.category.value != CATEGORY_HEALTH) {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    pagingVM.category.value = CATEGORY_HEALTH
                }

            }
            else{
                recyclerView.smoothScrollToPosition(0)

            }
        }
        chipGroup.getChildAt(4).setOnClickListener {
            if (pagingVM.category.value != CATEGORY_SCIENCE) {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    pagingVM.category.value = CATEGORY_SCIENCE
                }

            }
            else{
                recyclerView.smoothScrollToPosition(0)

            }
        }
        chipGroup.getChildAt(5).setOnClickListener {
            if (pagingVM.category.value != CATEGORY_SPORTS) {

                if (!isInternetAvailable(this)) {
                    showNoInternetDialog(this)
                } else {
                    pagingVM.category.value = CATEGORY_SPORTS
                }

            }
            else{
                recyclerView.smoothScrollToPosition(0)

            }
        }







//      -------------------------------------------------------------------------


//        viewModelRoom.getAllBookmarkedNews()

//        vm.currentCategory.observe(this){
//            lifecycleScope.launch {
//                vm.getTopHeadlines(COUNTRY_US,1)
//            }
//        }
//
//        if (isInternetAvailable(this)) {
//           vm.currentCategory.value = CATEGORY_GENERAL
//        } else {
//            showNoInternetDialog(this)
//        }
//
//        vm.isLoading.observe(this) { isLoading ->
//            if (isLoading) {
//                loader.visibility = View.VISIBLE
//                recyclerView.visibility = View.GONE
//            } else {
//                loader.visibility = View.GONE
//                recyclerView.visibility = View.VISIBLE
//            }
//        }
//
//        refreshLayout.setOnRefreshListener {
//            if (!isInternetAvailable(this)) {
//                showNoInternetDialog(this)
//                refreshLayout.isRefreshing = false
//
//            } else {
//                lifecycleScope.launch {
//                    vm.getTopHeadlines(COUNTRY_US,1)
//                    refreshLayout.isRefreshing = false
//                }
//            }
//
//        }
//
//        recyclerView = findViewById(R.id.recyclerView)
//
//
//
//        adapter = NewsAdapter(vm.articles, this,viewModelRoom)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = adapter
//
//        chipGroup = findViewById(R.id.chipGroup)
//        chipGroup.getChildAt(0).performClick()
//
//
//
//        vm.articles.observe(this) {
////            newsData = it
//            adapter.updateData(it)
//            adapter.notifyDataSetChanged()
//
//        }
//
//        chipGroup.getChildAt(0).setOnClickListener {
//            if (vm.currentCategory.value != CATEGORY_GENERAL) {
//
//                if (!isInternetAvailable(this)) {
//                    it.isSelected = false
//                    showNoInternetDialog(this)
//                } else {
//                    vm.currentCategory.value = CATEGORY_GENERAL
//                    adapter.notifyDataSetChanged()
//                    recyclerView.smoothScrollToPosition(0)
//                }
//            }
//            else{
//                recyclerView.smoothScrollToPosition(0)
//            }
//        }
//
//        chipGroup.getChildAt(1).setOnClickListener {
//            if (vm.currentCategory.value != CATEGORY_BUSINESS) {
//
//                if (!isInternetAvailable(this)) {
//                    showNoInternetDialog(this)
//                } else {
//                    vm.currentCategory.value = CATEGORY_BUSINESS
//                    adapter.notifyDataSetChanged()
//                    recyclerView.smoothScrollToPosition(0)
//                }
//            }
//            else{
//                recyclerView.smoothScrollToPosition(0)
//            }
//
//            }
//        chipGroup.getChildAt(2).setOnClickListener {
//        if (vm.currentCategory.value != CATEGORY_ENTERTAINMENT) {
//
//            if (!isInternetAvailable(this)) {
//                showNoInternetDialog(this)
//            } else {
//                vm.currentCategory.value = CATEGORY_ENTERTAINMENT
//                adapter.notifyDataSetChanged()
//                recyclerView.smoothScrollToPosition(0)
//            }
//        }
//        else{
//            recyclerView.smoothScrollToPosition(0)
//        }
//        }
//        chipGroup.getChildAt(3).setOnClickListener {
//            if (vm.currentCategory.value != CATEGORY_HEALTH) {
//
//                if (!isInternetAvailable(this)) {
//                    showNoInternetDialog(this)
//                } else {
//                    vm.currentCategory.value = CATEGORY_HEALTH
//                    adapter.notifyDataSetChanged()
//                    recyclerView.smoothScrollToPosition(0)
//                }
//
//            }
//            else{
//                recyclerView.smoothScrollToPosition(0)
//
//            }
//        }
//        chipGroup.getChildAt(4).setOnClickListener {
//            if (vm.currentCategory.value != CATEGORY_SCIENCE) {
//
//                if (!isInternetAvailable(this)) {
//                    showNoInternetDialog(this)
//                } else {
//                    vm.currentCategory.value = CATEGORY_SCIENCE
//                    adapter.notifyDataSetChanged()
//                    recyclerView.smoothScrollToPosition(0)
//                }
//
//            }
//            else{
//                recyclerView.scrollToPosition(0)
//
//            }
//        }
//        chipGroup.getChildAt(5).setOnClickListener {
//            if (vm.currentCategory.value != CATEGORY_SPORTS) {
//
//                if (!isInternetAvailable(this)) {
//                    showNoInternetDialog(this)
//                } else {
//                    vm.currentCategory.value = CATEGORY_SPORTS
//                    adapter.notifyDataSetChanged()
//                    recyclerView.smoothScrollToPosition(0)
//                }
//
//            }
//            else{
//                recyclerView.smoothScrollToPosition(0)
//
//            }
//        }

        bookmarkBtn.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
//            val intent = Intent(this, FlowsActivity::class.java)
//            startActivity(intent)
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
        builder.setPositiveButtonIcon(AppCompatResources.getDrawable(this,R.drawable.baseline_check_24))
        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            pagingAdapter.notifyDataSetChanged()

        }
    }
}
