package com.sanushradalage.cinema.movies.views

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanushradalage.cinema.R
import com.sanushradalage.cinema.movies.models.MovieDBClient
import com.sanushradalage.cinema.movies.models.MovieDBInterface
import com.sanushradalage.cinema.movies.repositories.MovieDataSourceRepository
import com.sanushradalage.cinema.movies.repositories.NetworkState
import com.sanushradalage.cinema.movies.viewmodels.MoviesViewModel
import com.sanushradalage.cinema.user.views.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MoviesViewModel
    lateinit var repository: MovieDataSourceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService: MovieDBInterface = MovieDBClient.getClient()

        repository = MovieDataSourceRepository(apiService)

        moviesViewModel = getViewModel()

        val movieAdapter = MoviePagedListAdapter(this)

        val gridLayoutManager = GridLayoutManager(this, 2)


        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType ==  movieAdapter.MOVIE_VIEW_TYPE) return  1
                else return 3
            }
        }

        movieList.layoutManager = gridLayoutManager
        movieList.setHasFixedSize(true)
        movieList.adapter = movieAdapter

        moviesViewModel.movieapgedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        moviesViewModel.networkState.observe(this, Observer {
            spin_kit.visibility = if (moviesViewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else  View.GONE
            error_msg.visibility = if (moviesViewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if(!moviesViewModel.listIsEmpty()){
                movieAdapter.setNetworkState(it)
            }
        })
    }


    private fun getViewModel(): MoviesViewModel
    {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MoviesViewModel(repository) as T
            }
        })[MoviesViewModel::class.java]
    }

    fun profileView(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}
