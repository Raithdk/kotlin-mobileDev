package com.example.myfirstkotlinapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstkotlinapp.data.Movie
import com.example.myfirstkotlinapp.data.MovieDatabase


class MainActivity : AppCompatActivity() {
    lateinit var db : MovieDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var movie1 = Movie(0, "Get Out", R.drawable.getout, "Jens Server tech")
        var movie2 = Movie(0, "Django Unchained", R.drawable.djangounchained, "Birgit ceo boss")
        var movie3 = Movie(0, "Gladiator", R.drawable.gladiator, "false")
        var movie4 = Movie(0, "Pulpfiction", R.drawable.pulpfiction, "true")

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

        var recyclerView: RecyclerView = findViewById(R.id.movieRecView)
        // The layout manager
        var recLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, recLayoutManager.getOrientation())
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = recLayoutManager
        recyclerView.adapter = MovieAdapter(movieList)

    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.textField)
        val message = editText.text.toString()
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("yeha", message)
        }
        startActivity(intent)
    }
}