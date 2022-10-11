package com.example.myfirstkotlinapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstkotlinapp.data.Movie
import com.example.myfirstkotlinapp.data.MovieDatabase
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    lateinit var db : MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO get movies from actual database instead of return
        var movieList : ArrayList<Movie> = populateDatabase()

        //  ####  Recycleview ####
        var recyclerView: RecyclerView = findViewById(R.id.movieRecView)
        // The layout manager
        var recLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, recLayoutManager.getOrientation())
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = recLayoutManager

        var adapter = MovieAdapter(movieList)
        recyclerView.adapter = adapter

        adapter.setOnClickListener(object : MovieAdapter.onRecClickListener{
            override fun recyclerViewListClicked(position: Int) {

                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {

                }
                intent.putExtra("title", "message2")
                startActivity(intent)
            }
        })

    }

    private fun populateDatabase() : ArrayList<Movie>{
        var movie1 = Movie(0, "Get Out", R.drawable.getout, "Movie about getting out")
        var movie2 = Movie(0, "Django Unchained", R.drawable.djangounchained, "Django is not chained")
        var movie3 = Movie(0, "Gladiator", R.drawable.gladiator, "Gladiator in a gladiator battle")
        var movie4 = Movie(0, "Pulpfiction", R.drawable.pulpfiction, "Juice with no pulp is fiction")

        var movieList : ArrayList<Movie> = arrayListOf()
        movieList.add(movie1)
        movieList.add(movie2)
        movieList.add(movie3)
        movieList.add(movie4)

        db = MovieDatabase.getAppDatabase(this)!!

        //Populates database if empty
        if(db.movieDao().getAll().isEmpty()){
            Log.i("DatabaseTest", "Ran Database Population")

            db.movieDao().insert(movie1)
            db.movieDao().insert(movie2)
            db.movieDao().insert(movie3)
            db.movieDao().insert(movie4)
        }
        return movieList
    }

    override fun recyclerViewListClicked(v: View, position: Int) {
        TODO("Not yet implemented")
    }
}
