package com.example.myfirstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val message = intent.getStringExtra("yeha")
        val viewoftext = findViewById<TextView>(R.id.movieFactsText)

        viewoftext.text = message
    }
}