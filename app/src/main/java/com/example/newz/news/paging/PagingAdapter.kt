package com.example.newz.news.paging

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
import com.example.newz.Utils
import com.example.newz.news.activities.ReadMoreActivity
import com.example.newz.news.db.News
import com.example.newz.news.models.Article
import com.example.newz.news.viewmodels.NewsVmDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


class PagingAdapter(private var context : Context, private var viewModel: NewsVmDb) : PagingDataAdapter<Article, PagingAdapter.PagingViewHolder>(
    COMPARATOR
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_rv,parent,false)
        return PagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)

        holder.bookmarkToggle.isSelected = hashmap[item?.title] == true

        holder.title.text = item?.title
        holder.metaa.text = Utils.parseTimestampManually(item?.publishedAt?:"0")
        holder.description.text = item?.description
        holder.author.text = "~ " + item?.author

        Glide.with(context) // or use 'this' if inside an Activity, 'requireContext()' in a Fragment
            .load(item?.urlToImage)
            .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
            .error(R.drawable.ic_error) // Optional: add an error image in case of failure
            .into(holder.imageView)

        holder.readMore.setOnClickListener {
            val intent = Intent(context, ReadMoreActivity::class.java)
            intent.putExtra("title", item?.title?:"")
            intent.putExtra("content", item?.content?:"")
            intent.putExtra("author", item?.author?:"")
            intent.putExtra("publishedAt", Utils.parseTimestampManually(item?.publishedAt?:"0"))
            intent.putExtra("image",item?.urlToImage?:"")
            context.startActivity(intent)
        }

        holder.bookmarkToggle.setOnClickListener {
            val news: News?

            if(!it.isSelected){
                news = item?.let { it1 ->
                    News(null,it1.author?:"",
                        it1.content?:"",it1.description,Utils.parseTimestampManually(it1.publishedAt),it1.source.toString(),it1.title,it1.url,it1.urlToImage)
                }

                if (news != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertBookmarkedNews(news)
                        if (item != null) {
                            hashmap[item.title]=true
                        }
                        it.isSelected = true
                        if(item?.title!=null){
                            hashmap[item.title]=true
                        }
                    }

                }
            }
            else{ CoroutineScope(Dispatchers.IO).launch {
                item?.title?.let { it1 ->
                    viewModel.deleteBookmarkedNewsByTitle(it1)
                    hashmap[item.title]= false
                    it.isSelected = false
                    hashmap[item.title]=false
                }
            }
            }

        }

    }

    companion object{

        val hashmap:HashMap<String,Boolean> = hashMapOf()

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