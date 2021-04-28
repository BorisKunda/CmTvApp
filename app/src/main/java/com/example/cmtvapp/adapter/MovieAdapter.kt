package com.example.cmtvapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtvapp.R
import com.example.cmtvapp.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieMList: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movie_item_ll, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieMList[position]
        holder.apply {
            titleTv.text = movie.title
            //TODO: 4/28/2021 poster+star
        }
    }

    override fun getItemCount(): Int = movieMList.size

    /**ViewHolder*/
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTv: TextView = itemView.findViewById(R.id.movie_title_tv)
        val posterIv: ImageView = itemView.findViewById(R.id.movie_poster_iv)
        val starIv: ImageView = itemView.findViewById(R.id.star_iv)

    }

}