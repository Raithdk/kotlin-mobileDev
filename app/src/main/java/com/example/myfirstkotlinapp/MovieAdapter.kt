package com.example.myfirstkotlinapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstkotlinapp.data.Movie


class MovieAdapter(private val dataSet: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var myListener: onRecClickListener

    interface onRecClickListener {
        fun recyclerViewListClicked(position: Int)
    }

    fun setOnClickListener(listener: onRecClickListener){
        myListener = listener
    }

    class ViewHolder(view: View, listener: onRecClickListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.holderText)
        val picView: ImageView = view.findViewById(R.id.holderImage)

        init {
            view.setOnClickListener{
                listener.recyclerViewListClicked(adapterPosition)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_view_holder, parent, false)
        return ViewHolder(view, myListener)
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


