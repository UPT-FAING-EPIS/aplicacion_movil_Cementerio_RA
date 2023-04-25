package com.example.mapsbasics2.common.entities


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mapsbasics2.R
/*
class ArtistAdapter: ListAdapter<Artist, RecyclerView.ViewHolder>(ArtistDiffCallback()) {

   private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_lite_)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //val binding = ItemLiteListBinding.bind(view)

    }

    class ArtistDiffCallback: DiffUtil.ItemCallback<Artist>(){
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist)= oldItem == newItem
        }



}*/
