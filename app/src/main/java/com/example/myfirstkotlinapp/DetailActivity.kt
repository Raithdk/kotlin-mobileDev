package com.example.myfirstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title")
        val cast = intent.getStringExtra("cast")
        val posterId = intent.getIntExtra("posterId",-1)

        val titleView = findViewById<TextView>(R.id.movieTitle)
        val posterView = findViewById<ImageView>(R.id.moviePosterDetail)
        val castView = findViewById<TextView>(R.id.castView)

        titleView.text = title
        castView.text = cast
        if (posterId != -1) {
            posterView.setImageResource(posterId)
        }
    }
}