package com.priyanshub.newsapp.presentation.breakingNews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.priyanshub.newsapp.R
import com.priyanshub.newsapp.databinding.ItemArticleLayoutBinding
import com.priyanshub.newsapp.domain.models.Article
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BreakingNewsAdapter(
    private val articleList: ArrayList<Article>,
    private val listener: ItemClickListener
): RecyclerView.Adapter<BreakingNewsAdapter.BreakingNewsViewHolder>() {
    inner class BreakingNewsViewHolder(val binding: ItemArticleLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding = ItemArticleLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BreakingNewsViewHolder(binding)
    }

    fun updateList(articleList: List<Article>){
        this.articleList.clear()
        this.articleList.addAll(articleList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        val article = articleList[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(article.urlToImage).placeholder(R.drawable.placeholder).into(holder.binding.ivNews)
            tvTitle.text = article.title
            tvDesc.text = article.description
            tvDate.text = article.publishedAt?.let { convertDateTime(it) }
            btnRead.setOnClickListener {
                listener.onItemClick(article,"READ")
            }
            btnSave.setOnClickListener {
                listener.onItemClick(article,"SAVE")
            }
        }
    }

    private fun convertDateTime(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy, hh:mm:ss a", Locale.getDefault())

        val date = inputFormat.parse(dateTimeString)
        return date?.let { outputFormat.format(it) }.toString()
    }

}
interface ItemClickListener{
    fun onItemClick(article: Article, btnClicked: String)
}