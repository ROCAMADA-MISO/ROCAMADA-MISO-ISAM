package com.example.vinyls_jetpack_application.ui.adapters

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
import com.example.vinilos.databinding.BandItemBinding
import com.example.vinilos.models.Band
import com.example.vinilos.ui.BandFragmentDirections


/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */

class BandsAdapter : RecyclerView.Adapter<BandsAdapter.BandViewHolder>(){

    var bands :List<Band> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        val withDataBinding: BandItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            BandViewHolder.LAYOUT,
            parent,
            false)
        return BandViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.band = bands[position]
        }
        holder.bind(bands[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = BandFragmentDirections.actionBandFragmentToBandDetailFragment(bands[position].id)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return bands.size
    }


    class BandViewHolder(val viewDataBinding: BandItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.band_item
        }
        fun bind(band: Band) {
            Glide.with(itemView)
                .load(band.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)

                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.bandImage)
        }
    }
}
