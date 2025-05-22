package com.foxerinc.movieapp.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.foxerinc.movieapp.R
import com.foxerinc.movieapp.core.domain.model.Movie
import com.foxerinc.movieapp.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = getParcelableExtra(intent, EXTRA_DATA, Movie::class.java)
        showDetailMovie(detailMovie)

    }

    private fun showDetailMovie(detailMovie: Movie?){
        detailMovie?.let {
            binding.tvTitle.text = detailMovie.movieName
            binding.tvReleaseDate.text = detailMovie.releaseDate
            binding.tvOverview.text = detailMovie.overview
            binding.tvRating.text = String.format(Locale.getDefault(), "%.1f/10", detailMovie.voteAverage)

            Glide.with(this@DetailMovieActivity)
                .load(detailMovie.posterUrl)
                .into(binding.imgPoster)

            Glide.with(this@DetailMovieActivity)
                .load(detailMovie.backdropUrl)
                .into(binding.imgBackdrop)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }


        }

    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}