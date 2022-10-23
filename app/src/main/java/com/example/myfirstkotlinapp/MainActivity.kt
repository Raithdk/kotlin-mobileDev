package com.example.myfirstkotlinapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstkotlinapp.data.Movie
import com.example.myfirstkotlinapp.data.MovieDatabase
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    lateinit var db : MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // TODO get movies from actual database instead of return

        var movieList: ArrayList<Movie> = populateDatabase()

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
                intent.putExtra("title", movieList[position].movieName)
                intent.putExtra("director", movieList[position].director)
                intent.putExtra("year", movieList[position].yr)
                intent.putExtra("posterId", movieList[position].moviePicId)
                intent.putExtra("cast", movieList[position].cast)
                startActivity(intent)
            }
        })

    }

    private fun populateDatabase() : ArrayList<Movie>{
        var movie1 = Movie(0, "Get Out", R.drawable.getout, "Jordan Peele", 2017, "Daniel Kaluuya, Allison Williams, LaKeith Stanfield")
        var movie2 = Movie(0, "Django Unchained", R.drawable.djangounchained, "Quentin Tarantino", 2012, "Jamie Foxx, Leonardo DiCaprio, Christoph Waltz")
        var movie3 = Movie(0, "Gladiator", R.drawable.gladiator, "Ridley Scott", 2000, "Russel Crowe, Joaquin Phoenix, Connie Nielsen")
        var movie4 = Movie(0, "Pulpfiction", R.drawable.pulpfiction, "Quentin Tarantino", 1994, "Uma Thurman, Samuel L. Jackson, John Travolta")
        var movie5 = Movie(0,"Shrek", R.drawable.shrek, "Andrew Adamson", 2001, "Mike Myers, Eddie Murhpy, Cameron Diaz")
        var movie6 = Movie(0,"Saving Private Ryan", R.drawable.ryan, "Steven Spielberg", 1998, "Tom Hanks, Matt Damon, Vin Diesel")


        var movieList : ArrayList<Movie> = arrayListOf()
        movieList.add(movie1)
        movieList.add(movie2)
        movieList.add(movie3)
        movieList.add(movie4)
        movieList.add(movie5)
        movieList.add(movie6)

        thread {
            db = MovieDatabase.getAppDatabase(this)!!

            //Populates database if empty
            if (db.movieDao().getAll().isEmpty()) {
                Log.i("DatabaseTest", "Ran Database Population")
                movieList.forEach {
                    db.movieDao().insert(it)
                }
            }
            print(db.movieDao().getByName("shrek"))
        }
        return movieList
    }
}

