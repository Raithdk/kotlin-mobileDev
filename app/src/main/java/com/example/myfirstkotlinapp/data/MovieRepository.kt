package com.example.myfirstkotlinapp.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies : Flow<List<Movie>> = movieDao.getAll()

    @WorkerThread
    suspend fun insert(movie : Movie){
        movieDao.insert(movie)
    }

    @WorkerThread
    suspend fun getMovieByName(name : String){
        movieDao.getByName(name)
    }


}