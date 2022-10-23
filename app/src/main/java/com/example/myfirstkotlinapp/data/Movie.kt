package com.example.myfirstkotlinapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey (autoGenerate = true) val uid : Int = 0,
    @ColumnInfo(name = "name") val movieName : String,
    @ColumnInfo(name = "imageurl") val moviePicId: Int,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "release_yr") val yr : Int,
    @ColumnInfo(name = "cast") val cast : String,

);