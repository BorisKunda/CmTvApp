package com.example.cmtvapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtvapp.R
import com.example.cmtvapp.adapter.MovieAdapter
import com.example.cmtvapp.viewmodel.MovieViewModel


class MovieListFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var moviesListRV: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie_list, container, false)

        buildUI(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        movieViewModel.getFavoriteMovies().observe(viewLifecycleOwner, {

            movieAdapter.movieMList = it.toMutableList()
            movieAdapter.notifyDataSetChanged()

        })

    }

    private fun buildUI(view: View) {

        setRecyclerView(view)

    }

    private fun setRecyclerView(view: View) {

        movieAdapter = MovieAdapter()

        moviesListRV = view.findViewById(R.id.movies_list_rv)

        moviesListRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context)
            adapter = movieAdapter
        }

    }

}