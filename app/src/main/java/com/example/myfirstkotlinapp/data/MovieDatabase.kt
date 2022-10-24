package com.example.myfirstkotlinapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myfirstkotlinapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Movie::class), version = 1,  exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        private class MovieDatabaseCallback(
            private val scope: CoroutineScope
        ) : Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.movieDao())
                    }
                }
            }

            suspend fun populateDatabase(movieDao: MovieDao){
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

                movieList.forEach {
                    movieDao.insert(it)
                }
            }
        }

        fun getAppDatabase(context: Context, scope: CoroutineScope): MovieDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "MovieDatabase"
                ).addCallback(MovieDatabaseCallback(scope)).build()
                INSTANCE = instance;
                instance
            }

        }
    }
}