package com.example.myfirstkotlinapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstkotlinapp.data.Movie

class MovieAdapter(private val dataSet: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>()
    {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView : TextView = view.findViewById(R.id.holderText)
            val picView : ImageView = view.findViewById(R.id.holderImage)
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_view_holder, parent, false)
            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.textView.text = dataSet[position].movieName
            viewHolder.picView.setImageResource(dataSet[position].moviePicId)
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size
    }
