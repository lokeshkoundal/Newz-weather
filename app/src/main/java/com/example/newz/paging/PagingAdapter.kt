package com.example.newz.paging

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newz.R
import com.example.newz.activities.ReadMoreActivity
import com.example.newz.models.Article


class PagingAdapter(private var context : Context) : PagingDataAdapter<Article,PagingAdapter.PagingViewHolder>(COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_rv,parent,false)
        return PagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item?.title
        holder.metaa.text = item?.publishedAt
        holder.description.text = item?.description
        holder.author.text = "~ " + item?.author

        Glide.with(context) // or use 'this' if inside an Activity, 'requireContext()' in a Fragment
            .load(item?.urlToImage)
            .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
            .error(R.drawable.ic_error) // Optional: add an error image in case of failure
            .into(holder.imageView)

        holder.readMore.setOnClickListener {
            val intent = Intent(context, ReadMoreActivity::class.java)
            intent.putExtra("title", item?.title)
            intent.putExtra("content", item?.content)
            intent.putExtra("author", item?.author)
            intent.putExtra("publishedAt", item?.publishedAt)
            intent.putExtra("image",item?.urlToImage)
            context.startActivity(intent)
        }
        holder.bookmarkToggle.setOnClickListener {
            // Handle bookmark toggle click
        }

    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class PagingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.articleImage)
        val title : TextView = itemView.findViewById(R.id.articleTitle)
        val metaa : TextView = itemView.findViewById(R.id.articleMeta)
        val description : TextView = itemView.findViewById(R.id.articleDescription)
        val readMore : TextView = itemView.findViewById(R.id.readMoreButton)
        val author : TextView = itemView.findViewById(R.id.author)
        val bookmarkToggle: ImageView = itemView.findViewById(R.id.bookmark)
    }
}