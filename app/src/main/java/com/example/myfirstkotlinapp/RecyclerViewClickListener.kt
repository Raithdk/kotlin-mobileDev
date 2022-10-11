package com.example.myfirstkotlinapp

import android.view.View

public interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v : View, position : Int)
}