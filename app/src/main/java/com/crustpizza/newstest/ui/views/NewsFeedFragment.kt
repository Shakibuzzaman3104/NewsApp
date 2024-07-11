package com.crustpizza.newstest.ui.views

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.crustpizza.newstest.R
import com.crustpizza.newstest.base.BaseFragment
import com.crustpizza.newstest.databinding.FragmentNewsFeedBinding

class NewsFeedFragment : BaseFragment<FragmentNewsFeedBinding>(R.layout.fragment_news_feed) {

    private val viewModel: NewsFeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

    }

}