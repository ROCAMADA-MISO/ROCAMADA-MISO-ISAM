package com.example.vinyls_jetpack_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumsItemBinding
import com.example.vinilos.models.Albums

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * Glide library to load an image by URL into an [ImageView]
 */

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    var albums :List<Albums> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val withDataBinding: AlbumsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumsViewHolder.LAYOUT,
            parent,
            false)
        return AlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albums = albums[position]
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumsViewHolder(val viewDataBinding: AlbumsItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.albums_item
        }
    }
}