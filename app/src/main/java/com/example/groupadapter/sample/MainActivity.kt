package com.example.groupadapter.sample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groupadapter.R
import com.example.groupadapter.adapter.AdapterViewInfo
import com.example.groupadapter.adapter.GroupAdapter
import com.example.groupadapter.adapter.UpdateType
import com.example.groupadapter.sample.adapterProvider.MoviesProvider
import com.example.groupadapter.sample.adapterProvider.UserProvider
import com.example.groupadapter.sample.dataProvider.Movie
import com.example.groupadapter.sample.dataProvider.User

class MainActivity : AppCompatActivity() {

    private var i = 0
    private val moviesProvider = MoviesProvider(this)
    private val userProvider = UserProvider(this)
    private lateinit var adapter: GroupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()

        findViewById<Button>(R.id.btn).setOnClickListener {
            if (i % 2 == 0) {
                val movie = Movie(
                    5,
                    "Thor: Ragnarok",
                    200, "17 March 2018", android.R.drawable.ic_dialog_email, moviesProvider
                )

                adapter.onUpdate(listOf(movie), UpdateType.APPEND())
            } else {
                val user = User("Userx", userProvider)
                adapter.onUpdate(listOf(user), UpdateType.APPEND())
            }
            i += 1
        }
    }

    private fun setUpView() {
        val movies = getMovies()
        val users = getUserNames()

        val recView = findViewById<RecyclerView>(R.id.recView)
        adapter = GroupAdapter(movies + users)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = adapter.onSpanSizeLookup

        recView.layoutManager = gridLayoutManager
        recView.adapter = adapter
    }


    private fun getMovies(): List<Movie> {


        AdapterViewInfo.registerProvider(1, moviesProvider)
        val listOfMovie = mutableListOf<Movie>()

        var movie = Movie(1, "Avengers", 500, "16 Feb 2018", R.drawable.baseline_movie_black_18dp, moviesProvider)
        listOfMovie.add(movie)

        movie = Movie(
            2,
            "Avengers: Age of Ultron",
            400,
            "17 March 2018",
            R.drawable.baseline_high_quality_black_18dp,
            moviesProvider
        )
        listOfMovie.add(movie)

        movie = Movie(3, "Iron Man 3", 550, "17 April 2018", R.drawable.baseline_account_circle_black_18dp, moviesProvider)
        listOfMovie.add(movie)

        return listOfMovie
    }

    private fun getUserNames(): MutableList<User> {

        val user = User("User1", userProvider)
        val user1 = User("User2", userProvider)
        val user2 = User("User3", userProvider)
        val user3 = User("User4", userProvider)

        AdapterViewInfo.registerProvider(2, userProvider)
        return mutableListOf(user, user1, user2, user3)
    }
}
