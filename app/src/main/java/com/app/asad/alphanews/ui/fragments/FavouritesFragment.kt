package com.app.asad.alphanews.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.asad.alphanews.R
import com.app.asad.alphanews.adapters.NewsAdapter
import com.app.asad.alphanews.databinding.FragmentFavouritesBinding
import com.app.asad.alphanews.ui.NewsActivity
import com.app.asad.alphanews.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    lateinit var newsAdapter: NewsAdapter
    lateinit var newsViewModel: NewsViewModel
    lateinit var binding: FragmentFavouritesBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesBinding.bind(view)


        /*       itemHeadlinesError = view.findViewById(R.id.itemHeadlinesError)
               val inflater =
                   requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
               val view: View = inflater.inflate(R.layout.item_error, null)
               retryButton = view.findViewById(R.id.retryButton)
               errorText = view.findViewById(R.id.errorText)*/
        newsViewModel = (activity as NewsActivity).newsViewModel
        setupFavouriteRecycler()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_favouritesFragment_to_articleFragment, bundle)

            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ){

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position=viewHolder.adapterPosition
                    val article=newsAdapter.differ.currentList[position]
                    newsViewModel.deleteArticle(article)
                    Snackbar.make(view,"Removed from Favourites",Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){
                            newsViewModel.addToFavourites(article)
                        }
                        show()
                    }
                }
            }
            ItemTouchHelper(itemTouchHelperCallback).apply {
                attachToRecyclerView(binding.recyclerFavourites)
            }
            newsViewModel.getFavoriteNews().observe(viewLifecycleOwner, Observer {articles->
                newsAdapter.differ.submitList(articles)

            })

        }

    }

    private fun setupFavouriteRecycler() {
        newsAdapter = NewsAdapter()
        binding.recyclerFavourites.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }
}