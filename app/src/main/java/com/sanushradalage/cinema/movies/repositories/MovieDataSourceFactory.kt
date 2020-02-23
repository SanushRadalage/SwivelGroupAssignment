package com.sanushradalage.cinema.movies.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sanushradalage.cinema.movies.models.MovieDBInterface
import com.sanushradalage.cinema.movies.models.Search
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService: MovieDBInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Search>() {

    val movieListDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Search> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)

        movieListDataSource.postValue(movieDataSource)
        return movieDataSource
    }


}