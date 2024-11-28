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
    companion object{
    val hashmap:HashMap<String,Boolean> = hashMapOf()

}

    fun updateData(newItems: NewsModel) {
        items = MutableLiveData(newItems)
//        hashmap.clear()
//        items.value?.articles?.forEach {
//            hashmap[it.title] = false
//        }
//
//        viewModel.getAllBookmarkedNews()
//        viewModel.allNews.value?.forEach {
//            hashmap[it.title] = true
//        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.article_rv, parent, false)

//        items.value?.articles?.forEach {
//            hashmap[it.title] = false
//        }
//
//        viewModel.getAllBookmarkedNews()
//        viewModel.allNews.value?.forEach {
//            hashmap[it.title] = true
//        }

        return NewsViewHolder(view)

    }

//    fun updateHashmap(hashmap: HashMap<String, Boolean>){
//        this.hashmap.clear()
//        this.hashmap.putAll(hashmap)
//    }


    override fun getItemCount(): Int {
        return items.value?.articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items.value?.articles?.get(position)

        holder.bookmarkToggle.isSelected = hashmap[currentItem?.title] == true

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
                    News(null,it1.author?:"",
                        it1.content?:"",it1.description,it1.publishedAt,it1.source.toString(),it1.title,it1.url,it1.urlToImage)
                }

                if (news != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertBookmarkedNews(news)
                        it.isSelected = true
                        if(currentItem?.title!=null){
                            hashmap[currentItem.title]=true
                        }
                    }

                }
            }
            else{ CoroutineScope(Dispatchers.IO).launch {
                 currentItem?.title?.let { it1 ->
                      viewModel.deleteBookmarkedNewsByTitle(it1)
                        it.isSelected = false
                         hashmap[currentItem.title]=false
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