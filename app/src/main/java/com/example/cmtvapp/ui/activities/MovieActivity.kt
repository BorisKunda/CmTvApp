package com.example.cmtvapp.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cmtvapp.R
import com.example.cmtvapp.ui.fragments.MovieListFragment
import com.example.cmtvapp.viewmodel.MovieViewModel

class MovieActivity : AppCompatActivity() {


    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fr_container_ll, MovieListFragment())
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_movies, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_popular -> {
                movieViewModel.onPopularMenuItemClicked()
                return true
            }

            R.id.item_latest -> {
                movieViewModel.onLatestMenuItemClicked()
                return true
            }
            R.id.item_favourites -> {
                movieViewModel.onFavouritesMenuItemClicked()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}