package com.example.newz.news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newz.R
import com.example.newz.databinding.ArticleRvBinding
import com.example.newz.news.activities.ReadMoreActivity
import com.example.newz.news.db.News
import com.example.newz.news.paging.PagingAdapter
import com.example.newz.news.viewmodels.NewsVmDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkAdapter(private var bookmarkedNews: List<News>, val context: Context, private var viewModel: NewsVmDb) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkViewHolder {
//        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.article_rv, parent, false)
//        return BookmarkViewHolder(view)

        val binding = ArticleRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {

        holder.binding.bookmark.isSelected = true

        val currentItem =  bookmarkedNews[position]
        holder.binding.bookMarkedNews = bookmarkedNews[position]

        Glide.with(context) // or use 'this' if inside an Activity, 'requireContext()' in a Fragment
                .load(bookmarkedNews[position].urlToImage)
                .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
                .error(R.drawable.ic_error) // Optional: add an error image in case of failure
                .into(holder.binding.articleImage)


        holder.binding.bookmark.setOnClickListener {

            val news = currentItem.let { it1 ->
                News(null,it1.author,
                    it1.content,it1.description,it1.publishedAt,it1.source,it1.title,it1.url,it1.urlToImage)
            }


            if(!it.isSelected){

                CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertBookmarkedNews(news)
                        it.isSelected = true
                }

            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
//                    news.id?.let { it1 -> viewModel.deleteBookmarkedNews(it1) }
                    viewModel.deleteBookmarkedNewsByTitle(currentItem.title)
                    it.isSelected = false
                    PagingAdapter.hashmap[currentItem.title] = false
                }
            }
        }

        holder.binding.readMoreButton.setOnClickListener(){
            val intent = Intent(context, ReadMoreActivity::class.java)
            intent.putExtra("title", currentItem.title)
            intent.putExtra("content", currentItem.content)
            intent.putExtra("author", currentItem.author)
            intent.putExtra("publishedAt", currentItem.publishedAt)
            intent.putExtra("image",currentItem.urlToImage)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookmarkedNews.size
    }

    inner class BookmarkViewHolder(val binding: ArticleRvBinding) : RecyclerView.ViewHolder(binding.root)
}