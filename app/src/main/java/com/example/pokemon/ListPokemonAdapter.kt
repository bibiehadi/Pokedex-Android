package com.example.pokemon

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.models.Pokemon
import com.google.android.material.chip.Chip

class ListPokemonAdapter(private val listPokemon: ArrayList<Pokemon>): RecyclerView.Adapter<ListPokemonAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPokemon: ImageView = itemView.findViewById(R.id.img_pokemon_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_pokemon_name)
        val tvNumber: TextView = itemView.findViewById(R.id.tv_pokemon_number)
        val chipType: Chip = itemView.findViewById(R.id.chip_pokemon_type)
        val ivFavorite: ImageView = itemView.findViewById(R.id.img_action_favorite)
        val ivShare: ImageView = itemView.findViewById(R.id.img_action_share)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onItemShareClickCallback: OnItemShareClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pokemon, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPokemon.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, number, typePokemon, description, photo, color) = listPokemon[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPokemon)
        holder.tvName.text = name
        holder.tvNumber.text = number
        holder.chipType.text = typePokemon

        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listPokemon[holder.adapterPosition])}
        holder.ivFavorite.setOnClickListener{
            Log.d("FAVORITE BUTTON", "BUTTON SHARE CLICKED")
        }
        holder.ivShare.setOnClickListener{
            onItemShareClickCallback.onItemClicked("This is my favorite Pokemon, $name")
        }

        holder.chipType.chipBackgroundColor = ColorStateList.valueOf(color)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Pokemon)
    }

    interface OnItemShareClickCallback {
        fun onItemClicked(data: String)
    }

}


