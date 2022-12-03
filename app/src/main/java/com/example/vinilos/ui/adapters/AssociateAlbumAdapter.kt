package com.example.vinyls_jetpack_application.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumsItemBinding
import com.example.vinilos.models.Albums
import com.example.vinilos.ui.AlbumsFragmentDirections

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * Glide library to load an image by URL into an [ImageView]
 */

class AssociateAlbumAdapter : RecyclerView.Adapter<AssociateAlbumAdapter.AssociateAlbumsViewHolder>() {

    var albums :List<Albums> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var selectedItemPos = -1
    var selectedItemId:Int=0
    var lastItemSelectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssociateAlbumsViewHolder {
        val withDataBinding: AlbumsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AssociateAlbumsViewHolder.LAYOUT,
            parent,
            false)
        return AssociateAlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AssociateAlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albums = albums[position]
        }
        if(position == selectedItemPos)
            holder.selectedBg()
        else
            holder.defaultBg()
        holder.bind(albums[position])
        holder.viewDataBinding.root.setOnClickListener {
            selectedItemPos = position
            if(lastItemSelectedPos == -1)
                lastItemSelectedPos = selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                lastItemSelectedPos = selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
        }
    }


    override fun getItemCount(): Int {
        return albums.size
    }

    class AssociateAlbumsViewHolder(val viewDataBinding: AlbumsItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.albums_item
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

        fun defaultBg() {
            viewDataBinding.albumItemLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        fun selectedBg() {
            viewDataBinding.albumItemLayout.setBackgroundColor(Color.parseColor("#EEE4C8"))
        }
    }
}