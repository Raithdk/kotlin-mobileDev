package com.example.myfirstkotlinapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie WHERE :movieID = uid")
    fun getById(movieID : Int): Movie

    @Query("SELECT * FROM Movie WHERE name LIKE '%'||:movieName||'%'")
    fun getByName(movieName : String?) : Movie

    @Insert
    fun insert(movie : Movie)

    @Delete
    fun delete(movie : Movie)
}