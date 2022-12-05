package com.example.vinilos.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumCreateCommentFragmentBinding
import com.example.vinilos.models.Albums
import com.example.vinilos.ui.AlbumDetailFragmentDirections
import com.example.vinyls_jetpack_application.ui.adapters.AlbumsAdapter

class AlbumsCommentAdapter : RecyclerView.Adapter<AlbumsCommentAdapter.AlbumsViewHolder>() {

    var albums :List<Albums> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsCommentAdapter.AlbumsViewHolder {
        val withDataBinding: AlbumCreateCommentFragmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumsCommentAdapter.AlbumsViewHolder.LAYOUT,
            parent,
            false)
        return AlbumsCommentAdapter.AlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumsCommentAdapter.AlbumsViewHolder, position: Int) {
        Log.d("Entro","Entro a metodo adapter")
        holder.viewDataBinding.also {
            it.albums = albums[position]
        }
        holder.bind(albums[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumDetailFragmentDirections.actionAlbumDetailFragmentToAlbumCommentFragment(albums[position].id)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumsViewHolder(val viewDataBinding: AlbumCreateCommentFragmentBinding) :
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
    }
}