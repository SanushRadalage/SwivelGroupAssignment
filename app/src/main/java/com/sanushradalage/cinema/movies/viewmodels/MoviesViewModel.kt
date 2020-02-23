package com.sanushradalage.cinema.movies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sanushradalage.cinema.movies.models.Search
import com.sanushradalage.cinema.movies.repositories.MovieDataSourceRepository
import com.sanushradalage.cinema.movies.repositories.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel(private val movieDataSourceRepository: MovieDataSourceRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieapgedList: LiveData<PagedList<Search>> by lazy {
        movieDataSourceRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

        val networkState : LiveData<NetworkState> by lazy {
            movieDataSourceRepository.getNetworkState()
        }

    fun listIsEmpty(): Boolean {
        return movieapgedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}