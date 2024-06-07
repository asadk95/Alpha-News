package com.app.asad.alphanews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.asad.alphanews.R
import com.app.asad.alphanews.models.Article
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    lateinit var articleImage: ImageView
    lateinit var articleTitle: TextView
    lateinit var articleSource: TextView
    lateinit var articleDescription: TextView
    lateinit var articleDateTime: TextView


    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ =AsyncListDiffer(this,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private var onItemClickListener:((Article)->Unit)?=null

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentArticle=differ.currentList[position]
        articleImage=holder.itemView.findViewById(R.id.articleImage)
        articleSource=holder.itemView.findViewById(R.id.articleSource)
        articleTitle=holder.itemView.findViewById(R.id.articleTitle)
        articleDescription=holder.itemView.findViewById(R.id.articleDescription)
        articleDateTime=holder.itemView.findViewById(R.id.articleDateTime)
        holder.itemView.apply{
               Glide.with(this).load(currentArticle.urlToImage).into(articleImage)
            articleSource.text=currentArticle.source?.name
            articleTitle.text=currentArticle.title
            articleDescription.text=currentArticle.description
            articleDateTime.text=currentArticle.publishedAt

            setOnClickListener {
                onItemClickListener?.let {
                    it (currentArticle)
                }
            }
        }
    }
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }
}