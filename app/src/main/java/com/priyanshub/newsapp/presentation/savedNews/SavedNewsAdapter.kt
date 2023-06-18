package com.priyanshub.newsapp.presentation.savedNews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.priyanshub.newsapp.R
import com.priyanshub.newsapp.databinding.ItemArticleLayoutBinding
import com.priyanshub.newsapp.databinding.ItemSavedArticleLayoutBinding
import com.priyanshub.newsapp.domain.models.Article
import java.text.SimpleDateFormat
import java.util.*

class SavedNewsAdapter(
    private val articleList: List<Article>,
    private val listener: SavedItemClickListener
): RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder>() {
    inner class SavedNewsViewHolder(val binding: ItemSavedArticleLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val binding = ItemSavedArticleLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SavedNewsViewHolder(binding)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val article = articleList[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(article.urlToImage).placeholder(R.drawable.placeholder).into(holder.binding.ivNews)
            tvTitle.text = article.title
            ivDelete.setOnClickListener {
                listener.onItemClick(article,"DELETE")
            }
            clSavedArticle.setOnClickListener {
                listener.onItemClick(article,"READ")
            }
        }
    }
}

interface SavedItemClickListener{
    fun onItemClick(article: Article,btnClicked: String)
}