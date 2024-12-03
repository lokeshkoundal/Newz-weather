package com.example.newz.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newz.models.Article
import com.example.newz.network.NewsApiService

class NewzPagingSource(private val newsApiService: NewsApiService) : PagingSource<Int,Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try{
            val position = params.key?:1
            val response = newsApiService.getTopHeadlines("us","general",position).body()
            return response.let {
                it?.let { it1 ->
                    LoadResult.Page(
                        data = it1.articles,
                        prevKey = if (position == 1) null else position - 1,
                        nextKey = if (position == response?.totalResults) null else position+1)
                }
            }!!
        }
        catch (e:Exception){
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