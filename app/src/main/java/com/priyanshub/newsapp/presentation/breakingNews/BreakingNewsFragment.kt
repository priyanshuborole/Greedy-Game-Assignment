package com.priyanshub.newsapp.presentation.breakingNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.priyanshub.newsapp.R
import com.priyanshub.newsapp.databinding.FragmentBreakingNewsBinding
import com.priyanshub.newsapp.domain.models.Article
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.filterList
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BreakingNewsFragment : Fragment() {
    private val viewModel: BreakingNewsViewModel by viewModels()
    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var breakingNewsAdapter: BreakingNewsAdapter

    private val articleList: ArrayList<Article> by lazy { ArrayList() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(emptyList())

        binding.btnSavedArticle.setOnClickListener {
            findNavController().navigate(R.id.action_breakingNewsFragment_to_savedNewsFragment)
        }


        binding.etSearch.doAfterTextChanged { string ->
            if (articleList.isNotEmpty() && string.toString().isNotEmpty()) {
                val filteredList: List<Article> = articleList.filterList {
                    title?.lowercase()?.contains(string.toString().lowercase())!!
                }
                breakingNewsAdapter.updateList(filteredList)
            } else {
                breakingNewsAdapter.updateList(articleList)
            }
        }

        binding.ivFilter.setOnClickListener {
            val sortedList: List<Article> = articleList.sortedBy { article ->
                article.publishedAt?.let { datetime ->
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(
                        datetime
                    )
                }
            }
            breakingNewsAdapter.updateList(sortedList)


            Snackbar.make(binding.root, "Sorted By Date", Snackbar.LENGTH_SHORT).show()
        }


        viewModel.showProgress.observe(viewLifecycleOwner) { showProgress ->
            binding.paginationProgressBar.visibility = if (showProgress) View.VISIBLE else View.GONE
        }

        viewModel.breakingNews.observe(viewLifecycleOwner) { breakingNews ->
            val articles = breakingNews.articles
            articleList.clear()
            articleList.addAll(articles)
            breakingNewsAdapter.updateList(articles)
        }
    }

    private fun setupRecyclerView(list: List<Article>) {
        breakingNewsAdapter = BreakingNewsAdapter(ArrayList(list), object : ItemClickListener {
            override fun onItemClick(article: Article, btnClicked: String) {
                when (btnClicked) {
                    "READ" -> {
                        val bundle = Bundle().apply {
                            putSerializable("article", article)
                        }
                        findNavController().navigate(
                            R.id.action_breakingNewsFragment_to_articleFragment,
                            bundle
                        )
                    }

                    "SAVE" -> {
                        viewModel.saveArticle(article)
                        Snackbar.make(binding.root, "Article Saved", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        })
        binding.rvBreakingNews.apply {
            adapter = breakingNewsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}