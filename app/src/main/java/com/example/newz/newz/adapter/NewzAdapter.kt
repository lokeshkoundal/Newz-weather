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
import com.example.newz.newz.ReadMoreActivity
import com.example.newz.newz.models.NewsModel

class NewsAdapter(private var items : MutableLiveData<NewsModel>, private  var context : Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

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
            holder.author.text = "~" + currentItem.author

            Glide.with(context) // or use 'this' if inside an Activity, 'requireContext()' in a Fragment
                .load(currentItem.urlToImage)
                .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
                .error(R.drawable.ic_error) // Optional: add an error image in case of failure
                .into(holder.imageView)
        }

        holder.bookmarkToggle.setOnClickListener {
            it.isSelected = !it.isSelected
        }

        holder.readMore.setOnClickListener(){
            val intent:Intent = Intent(context, ReadMoreActivity::class.java)
            intent.putExtra("title", currentItem?.title)
            intent.putExtra("content", currentItem?.content)
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