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
        val description = intent.getStringExtra("description")
        val posterId = intent.getIntExtra("posterId",-1)

        val titleView = findViewById<TextView>(R.id.movieTitle)
        val descriptionView = findViewById<TextView>(R.id.movieDescription)
        val posterView = findViewById<ImageView>(R.id.moviePosterDetail)

        titleView.text = title
        descriptionView.text = description
        if (posterId != -1) {
            posterView.setImageResource(posterId)
        }
    }
}