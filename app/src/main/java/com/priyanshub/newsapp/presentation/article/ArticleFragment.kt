package com.priyanshub.newsapp.presentation.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.priyanshub.newsapp.R
import com.priyanshub.newsapp.databinding.FragmentArticleBinding
import com.priyanshub.newsapp.presentation.breakingNews.BreakingNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    val args : ArticleFragmentArgs by navArgs()
    private val viewModel: BreakingNewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentArticleBinding.inflate(layoutInflater,container,false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolBarLayout)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolBarLayout.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.root).load(args.article.urlToImage).into(binding.ivNews)
        binding.toolBarLayout.title = args.article.title
        binding.tvAuthor.text = args.article.author
        binding.tvDesc.text = args.article.content
        binding.btnSave.setOnClickListener {
            viewModel.saveArticle(article = args.article)
        }
    }
}