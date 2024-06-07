package com.app.asad.alphanews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.app.asad.alphanews.R
import com.app.asad.alphanews.databinding.ActivityNewsBinding
import com.app.asad.alphanews.databinding.FragmentArticleBinding
import com.app.asad.alphanews.repository.NewsRepository
import com.app.asad.alphanews.ui.NewsActivity
import com.app.asad.alphanews.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment() {

lateinit var newsViewModel: NewsViewModel
val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentArticleBinding.bind(view)
        newsViewModel=(activity as NewsActivity).newsViewModel
        val article= args.article
        binding.webView.apply {
            webViewClient= WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }
        binding.fab.setOnClickListener {
            newsViewModel.addToFavourites(article)
            Snackbar.make(view,"Added to favourites",Snackbar.LENGTH_LONG).show()
        }
    }

}