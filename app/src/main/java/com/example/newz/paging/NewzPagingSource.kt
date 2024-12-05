package com.example.newz.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newz.models.Article
import com.example.newz.network.NewsApiService

class NewzPagingSource(private val newsApiService: NewsApiService, private var category : String) : PagingSource<Int,Article>() {

    companion object{
        val isLoading = MutableLiveData(true)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try{
            isLoading.value = true
            val pageNumber = params.key?:1
            val response = newsApiService.getTopHeadlines("us", category,pageNumber).body()
//            response?.articles = response?.articles?.filter { it.title != "[Removed]" }!!

            return response.let {
                it?.let { it1 ->
                    LoadResult.Page(
                        data = it1.articles,
                        prevKey = if (pageNumber == 1) null else pageNumber - 1,
                        nextKey = if (pageNumber == response?.totalResults) null else pageNumber+1)
                }
                }!!.also {
                isLoading.value = false
            }
        }
        catch (e:Exception){
            isLoading.value = false
            return LoadResult.Error(e)

        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
                state.closestPageToPosition(it)?.prevKey?.plus(1)
                    ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
            }
        }
}