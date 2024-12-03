package com.example.newz.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newz.network.NewsApiService
import com.example.newz.paging.NewzPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagerVM @Inject constructor(private val newsApiService: NewsApiService): ViewModel() {

    val list = getHeadLines().cachedIn(viewModelScope)

    private fun getHeadLines() = Pager(config = PagingConfig(pageSize = 5, maxSize = 50, prefetchDistance = 1),
        pagingSourceFactory = { NewzPagingSource(newsApiService) }
    ).flow
}