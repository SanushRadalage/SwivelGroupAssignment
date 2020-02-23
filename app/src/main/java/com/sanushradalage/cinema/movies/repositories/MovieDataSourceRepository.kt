package com.sanushradalage.cinema.movies.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sanushradalage.cinema.movies.models.Movie
import com.sanushradalage.cinema.movies.models.MovieDBInterface
import com.sanushradalage.cinema.movies.models.POST_PER_PAGE
import com.sanushradalage.cinema.movies.models.Search
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.ExecutorCompletionService

class MovieDataSourceRepository(private val apiService: MovieDBInterface)
{
    lateinit var moviePagedList: LiveData<PagedList<Search>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable): LiveData<PagedList<Search>> {
        movieDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            movieDataSourceFactory.movieListDataSource, MovieDataSource::networkState
        )
    }
}