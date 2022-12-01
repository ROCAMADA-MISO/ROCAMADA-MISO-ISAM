package com.example.vinyls_jetpack_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.ArtistAlbumItemBinding
import com.example.vinilos.models.Albums

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * Glide library to load an image by URL into an [ImageView]
 */

class ArtistAlbumsAdapter : RecyclerView.Adapter<ArtistAlbumsAdapter.ArtistAlbumsViewHolder>() {

    var albums :List<Albums> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumsViewHolder {
        val withDataBinding: ArtistAlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistAlbumsViewHolder.LAYOUT,
            parent,
            false)
        return ArtistAlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistAlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albums = albums[position]
        }
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class ArtistAlbumsViewHolder(val viewDataBinding: ArtistAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_album_item
        }
        fun bind(albums: Albums) {
            Glide.with(itemView)
                .load(albums.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)

                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.albumsCover)
        }
    }
}