package com.priyanshub.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.priyanshub.newsapp.commons.Resource
import com.priyanshub.newsapp.databinding.ActivityMainBinding
import com.priyanshub.newsapp.domain.usecase.GetBreakingNewsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getBreakingNewsUseCase: GetBreakingNewsUseCase

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch(Dispatchers.IO){

        }
    }
}