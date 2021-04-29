package com.example.cmtvapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtvapp.R
import com.example.cmtvapp.adapter.MovieAdapter
import com.example.cmtvapp.model.Movie
import com.example.cmtvapp.viewmodel.MovieViewModel


class MovieListFragment : Fragment(), MovieAdapter.OnRVItemClickListener {

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

        //default
        movieViewModel.loadPopularMovies()
        observeLiveData(movieViewModel)

    }

    private fun buildUI(view: View) {
        setRecyclerView(view)
    }


    private fun observeLiveData(vm: MovieViewModel) {

        /**click events*/
        vm.popularMenuItemClickSld.observe(viewLifecycleOwner, {//index 3 = league
            vm.loadPopularMovies()
        })

        vm.latestMenuItemClickSld.observe(viewLifecycleOwner, {//index 3 = unholy
            vm.loadLatestMovies()
        })

        vm.favouriteMenuItemClickSld.observe(viewLifecycleOwner, {
            vm.loadFavouriteMovies()
        })

        /**movie list*/
        vm.moviesListLd.observe(viewLifecycleOwner, {

            movieAdapter.apply {
                movieMList.clear()
                movieMList = it.toMutableList()
                notifyDataSetChanged()
            }

        })

    }

    private fun setRecyclerView(view: View) {

        movieAdapter = MovieAdapter(this)

        moviesListRV = view.findViewById(R.id.movies_list_rv)

        moviesListRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context)
            addItemDecoration(
                DividerItemDecoration(
                    view.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = movieAdapter
        }

    }

    override fun onRVItemClick(movie: Movie) {

        movieViewModel.apply {
            selectedMovieMld.value = movie
            openMovieDetailsScreenSld.call()
        }

    }

}