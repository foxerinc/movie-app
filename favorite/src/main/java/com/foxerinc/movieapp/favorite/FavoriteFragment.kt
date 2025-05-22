package com.foxerinc.movieapp.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.foxerinc.movieapp.core.ui.MovieAdapter
import com.foxerinc.movieapp.favorite.databinding.FragmentFavoriteBinding
import com.foxerinc.movieapp.detail.DetailMovieActivity
import com.foxerinc.movieapp.di.FavoriteModuleDependencies
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors


class FavoriteFragment: Fragment() {
    private lateinit var viewModel: FavoriteViewModel

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            FavoriteModuleDependencies::class.java
        )
        val movieUseCase = entryPoint.movieUseCase()

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return FavoriteViewModel(movieUseCase) as T
                }
            }
        )[FavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }


            viewModel.favoriteMovie.observe(viewLifecycleOwner) { dataTourism ->
                movieAdapter.submitList(dataTourism)
                binding.viewEmpty.root.visibility =
                    if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

}