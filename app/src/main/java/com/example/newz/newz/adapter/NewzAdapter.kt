package com.example.newz.newz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newz.R
import com.example.newz.db.News
import com.example.newz.db.NewsVmDb
import com.example.newz.newz.ReadMoreActivity
import com.example.newz.newz.models.NewsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsAdapter(private var items : MutableLiveData<NewsModel>, private  var context : Context,var viewModel: NewsVmDb) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

//    private lateinit  var items: NewsModel


    fun updateData(newItems: NewsModel) {
        items = MutableLiveData(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.article_rv, parent, false)
        return NewsViewHolder(view)

    }


    override fun getItemCount(): Int {
        return items.value?.articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items.value?.articles?.get(position)

        if (currentItem != null) {
            holder.title.text = currentItem.title
            holder.metaa.text = currentItem.publishedAt
            holder.description.text = currentItem.description
            holder.author.text = "~ " + currentItem.author

            Glide.with(context) // or use 'this' if inside an Activity, 'requireContext()' in a Fragment
                .load(currentItem.urlToImage)
                .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
                .error(R.drawable.ic_error) // Optional: add an error image in case of failure
                .into(holder.imageView)

        }


        holder.bookmarkToggle.setOnClickListener {
            var news: News? = null

            if(!it.isSelected){
                 news = currentItem?.let { it1 ->
                    News(0,it1.author,
                        it1.content,it1.description,it1.publishedAt,it1.source.toString(),it1.title,it1.url,it1.urlToImage)
                }

                if (news != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertBookmarkedNews(news)
                        it.isSelected = true

                    }

                }
            }
            else{ CoroutineScope(Dispatchers.IO).launch {
                    if (news != null) {
                        news.id?.let { it1 -> viewModel.deleteBookmarkedNews(it1) }
                        it.isSelected = false
                    }
                }
            }




        }

        holder.readMore.setOnClickListener(){
            val intent = Intent(context, ReadMoreActivity::class.java)
            intent.putExtra("title", currentItem?.title)
            intent.putExtra("content", currentItem?.content)
            intent.putExtra("author", currentItem?.author)
            intent.putExtra("publishedAt", currentItem?.publishedAt)
            intent.putExtra("image",currentItem?.urlToImage)
            context.startActivity(intent)
        }
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.articleImage)
        val title : TextView = itemView.findViewById(R.id.articleTitle)
        val metaa : TextView = itemView.findViewById(R.id.articleMeta)
        val description : TextView = itemView.findViewById(R.id.articleDescription)
        val readMore : TextView = itemView.findViewById(R.id.readMoreButton)
        val author :TextView = itemView.findViewById(R.id.author)
        val bookmarkToggle:ImageView = itemView.findViewById(R.id.bookmark)

        

    }
}