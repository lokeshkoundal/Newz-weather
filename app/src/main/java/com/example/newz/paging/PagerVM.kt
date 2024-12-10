package com.example.newz.paging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newz.models.Article
import com.example.newz.network.NewsApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PagerVM @Inject constructor(private val newsApiService: NewsApiService): ViewModel() {

     var category : MutableLiveData<String> = MutableLiveData("general")
//     lateinit  var list : Flow<PagingData<Article>>

     fun getHeadLines(): Flow<PagingData<Article>> {

          return Pager(config = PagingConfig(pageSize = 10, maxSize = 100, prefetchDistance = 10),
            pagingSourceFactory = { category.value?.let { NewzPagingSource(newsApiService, it) }!! }
        ).flow.cachedIn(viewModelScope)

     }
}