package com.example.cmtvapp.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.cmtvapp.R
import com.example.cmtvapp.ui.fragments.MovieDetailsFragment
import com.example.cmtvapp.ui.fragments.MovieListFragment
import com.example.cmtvapp.utils.UtilMethods
import com.example.cmtvapp.viewmodel.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()
    private var shouldHideMenu = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportFragmentManager.beginTransaction().replace(R.id.fr_container_ll, MovieListFragment())
            .commit()

        observeLiveData(movieViewModel)

    }

    private fun observeLiveData(vm: MovieViewModel) {

        movieViewModel.openMovieDetailsScreenSld.observe(this, {
            openMovieDetailsScreen()
        })

    }

    /**menu*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        UtilMethods.printI("inv")

        return if (!shouldHideMenu) {
            menuInflater.inflate(R.menu.menu_movies, menu)
            true
        } else {
            false
        }
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

    /**navigation*/

    fun openMovieDetailsScreen() {

        shouldHideMenu = true

        invalidateOptionsMenu()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container_ll, MovieDetailsFragment())
            .addToBackStack("details_screen_fragment")
            .commit()
    }

    override fun onBackPressed() {

        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            shouldHideMenu = false
            invalidateOptionsMenu()
            supportFragmentManager.popBackStack()
        }

    }

}