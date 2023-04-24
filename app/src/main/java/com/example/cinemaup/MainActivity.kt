package com.example.cinemaup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.cinemaup.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
//import androidx.viewbinding.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException


//fun createJson() = Json {
//    isLenient = true
//    ignoreUnknownKeys = true
//    useAlternativeNames = false
//}
//
//private const val TAG = "MainActivity/"
//
//private const val SEARCH_API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"//BuildConfig.API_KEY
//private const val NowPlaying_MOVIE_SEARCH_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=${SEARCH_API_KEY}&language=en-US&page=1.json"
//private const val Upcoming_MOVIE_SEARCH_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=${SEARCH_API_KEY}&language=en-US&page=1.json"
////    "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=${SEARCH_API_KEY}"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val watchListFragment: Fragment = WatchListFragment()
        val gamesFragment: Fragment = GamesFragment()
        val exploreFragment: Fragment = ExploreFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)


        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_liked -> fragment = watchListFragment
                R.id.nav_game -> fragment = gamesFragment
                R.id.nav_home -> fragment = exploreFragment

            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_home

    }

    private fun replaceFragment(legDayListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, legDayListFragment)
        fragmentTransaction.commit()
    }

}

//    private val movieMutableList = mutableListOf<Movie>()
//
//    private lateinit var moviesRecyclerView: RecyclerView
//    private lateinit var binding: ActivityMainBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        moviesRecyclerView = findViewById(R.id.nowPlayingRV)
//        val movieAdapter = MovieAdapter(this, movieMutableList)
//        moviesRecyclerView.adapter = movieAdapter
//
//        moviesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)//.also {
////            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
////            moviesRecyclerView.addItemDecoration(dividerItemDecoration)
//
////        }
//
//        val client = AsyncHttpClient()
//        client.get(NowPlaying_MOVIE_SEARCH_URL, object : JsonHttpResponseHandler() {
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                response: String?,
//                throwable: Throwable?
//            ) {
//                Log.e(TAG, "Failed to fetch movies: $statusCode")
//            }
//
//            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
//                Log.i(TAG, "Successfully fetched movies: ${json}")
//                try {
//
//                    //note: Deserializes the given JSON string into a value of type T using the given deserializer.
//                    //note:public final fun <T> decodeFromString(
//                    //    deserializer: DeserializationStrategy<T>,
//                    //    string: String ): T
//
//                    //note: takes the json response and converts it to a list of movies
//                    val movieResponse = createJson().decodeFromString(
//                        MovieResponse.serializer(),
//                        json.jsonObject.toString()
//                    )
//
//                    //note: Collection and List are the same
//                    //note: this code block adds the list of movies from MovieResponse to the mutable list of movies using the addAll method
//                    movieResponse.movies?.let { listMovies ->
//                        movieMutableList.addAll(listMovies)
//                        movieAdapter.notifyDataSetChanged()
//                    }
//
//
//                } catch (e: JSONException) {
//                    Log.e(TAG, "Exception: $e")
//                }
//            }
//
//        })
//
//
//    }
