package com.example.newz.newz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newz.R

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.article_rv, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.BookmarkViewHolder, position: Int) {
//        return bookmarkedNews.value?.articles?.size ?: 0
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}