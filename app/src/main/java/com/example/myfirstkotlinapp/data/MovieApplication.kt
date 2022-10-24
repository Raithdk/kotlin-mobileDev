package com.example.myfirstkotlinapp.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MovieApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { MovieDatabase.getAppDatabase(this, applicationScope)}
    val repository by lazy { MovieRepository(database.movieDao())}
}