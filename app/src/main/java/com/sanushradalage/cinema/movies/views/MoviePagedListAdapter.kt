package com.sanushradalage.cinema.movies.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanushradalage.cinema.R
import com.sanushradalage.cinema.movies.models.Search
import com.sanushradalage.cinema.movies.repositories.NetworkState
import kotlinx.android.synthetic.main.card_view.view.*
import kotlinx.android.synthetic.main.network_state_layout.view.*

class MoviePagedListAdapter(public val context: Context) : PagedListAdapter<Search, RecyclerView.ViewHolder>(MovieDiffCallback()){

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        return if(viewType == MOVIE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.card_view, parent, false)
            MovieItemViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.network_state_layout, parent, false)
            NetworkStateItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MOVIE_VIEW_TYPE)
        {
            (holder as MovieItemViewHolder).bind(getItem(position), context)
        }
        else{
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1 ){
            NETWORK_VIEW_TYPE
        }
        else {
            MOVIE_VIEW_TYPE
        }
    }

    class MovieDiffCallback: DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }
    }

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(search: Search?, context: Context){
            itemView.title.text = search?.Title

            Glide.with(itemView.context)
                .load(search?.Poster)
                .into(itemView.imageView)
        }
    }

    class NetworkStateItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bind(networkState: NetworkState?) {

            if(networkState != null && networkState == NetworkState.LOADING)
            {
                itemView.spin_kit.visibility = View.VISIBLE
            }
            else
            {
                itemView.spin_kit.visibility = View.GONE
            }

            if(networkState != null && networkState ==NetworkState.ERROR){
                itemView.error_msg.visibility = View.VISIBLE
                itemView.error_msg.text = networkState.message
            }
            else if (networkState != null && networkState ==NetworkState.ENDOFLIST){
                itemView.error_msg.visibility = View.VISIBLE
                itemView.error_msg.text = networkState.message
            }
            else{
                itemView.error_msg.visibility = View.GONE
            }
        }
    }

    fun setNetworkState(newNetworkState: NetworkState){
        val previousState: NetworkState? = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = networkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow){
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            }
            else {
                notifyItemInserted(super.getItemCount())
            }
        }
        else if (hasExtraRow && previousState != newNetworkState){
            notifyItemChanged(itemCount -1)
        }
    }
}