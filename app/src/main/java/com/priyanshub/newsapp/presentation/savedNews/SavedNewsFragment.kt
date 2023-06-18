package com.priyanshub.newsapp.presentation.savedNews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.priyanshub.newsapp.R
import com.priyanshub.newsapp.databinding.FragmentSavedNewsBinding
import com.priyanshub.newsapp.domain.models.Article
import com.priyanshub.newsapp.presentation.breakingNews.BreakingNewsAdapter
import com.priyanshub.newsapp.presentation.breakingNews.BreakingNewsViewModel
import com.priyanshub.newsapp.presentation.breakingNews.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var savedNewsAdapter: SavedNewsAdapter
    private val viewModel: SavedNewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSavedNews()?.observe(viewLifecycleOwner){
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(list: List<Article>) {
        savedNewsAdapter = SavedNewsAdapter(ArrayList(list),object : SavedItemClickListener{
            override fun onItemClick(article: Article, btnClicked: String) {
                when(btnClicked){
                    "DELETE"->{
                        viewModel.deleteArticle(article)
                    }
                    "READ" -> {
                        val bundle = Bundle().apply {
                            putSerializable("article",article)
                        }
                        findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment,bundle)
                    }
                }
            }

        })
        binding.rvSavedNews.apply {
            adapter = savedNewsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}