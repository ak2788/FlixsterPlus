package com.example.flixsterplus

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter (private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val overviewTextView: TextView = itemView.findViewById(R.id.overviewTextView)
        val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title
        holder.overviewTextView.text = movie.overview

        Glide.with(holder.itemView.context)
            .load(movie.getPosterUrl())
            .placeholder(R.drawable.placeholder)
            .into(holder.posterImageView)
    }

    override fun getItemCount(): Int = movies.size


}