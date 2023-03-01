package com.example.bangkitttt1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangkitttt1.R
import com.example.bangkitttt1.model.Gem

class ListGemAdapter(private val listGem: ArrayList<Gem>) : RecyclerView.Adapter<ListGemAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Gem)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePhoto: ImageView = itemView.findViewById(R.id.ivCard)
        var tvTitle: TextView = itemView.findViewById(R.id.tvCardTitle)
        var tvDescription: TextView = itemView.findViewById(R.id.tvCardDescription)
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_main, parent , false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder : ListViewHolder , position : Int) {
        val (title, description , imageURL ) = listGem[position]
        Glide.with(holder.itemView.context)
            .load(imageURL)
            .into(holder.imagePhoto)
        holder.tvTitle.text = title
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listGem[holder.adapterPosition])
        }
    }

    override fun getItemCount() : Int = listGem.size

    fun setOnItemClickCallBack(onItemClickCallback : OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}