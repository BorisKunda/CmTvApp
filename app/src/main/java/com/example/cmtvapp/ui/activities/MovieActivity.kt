package com.example.cmtvapp.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtvapp.R
import com.example.cmtvapp.ui.fragments.MovieListFragment
import com.example.cmtvapp.viewmodel.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fr_container_ll, MovieListFragment()).commit()

    }
}