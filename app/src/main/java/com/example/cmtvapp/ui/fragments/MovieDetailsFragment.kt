package com.example.cmtvapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.cmtvapp.R
import com.example.cmtvapp.utils.Constants
import com.example.cmtvapp.viewmodel.MovieViewModel


class MovieDetailsFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var posterIv: ImageView
    private lateinit var titleTv: TextView
    private lateinit var overviewTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_movie_details, container, false)

        buildUI(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData(movieViewModel)


    }

    private fun buildUI(view: View) {

        posterIv = view.findViewById(R.id.movie_details_poster_iv)
        titleTv = view.findViewById(R.id.movie_details_title_tv)
        overviewTv = view.findViewById(R.id.movie_details_overview_tv)

    }

    private fun observeLiveData(vm: MovieViewModel) {

        vm.selectedMovieLd.observe(viewLifecycleOwner, {

            //poster
            Glide.with(posterIv.context).load("${Constants.POSTER_BASE_PATH}${it.posterPath}")
                .placeholder(R.drawable.ic_image_place_holder).into(posterIv)

            titleTv.text = it.title

            overviewTv.text = it.overview

        })

    }

}