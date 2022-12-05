package com.example.vinilos.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.AlbumCreateCommentFragmentBinding
import com.example.vinilos.models.Collector
import com.example.vinilos.viewmodels.AlbumCommentViewModel
import com.example.vinyls_jetpack_application.ui.adapters.AlbumsAdapter


class CreateCommentAlbumFragment : Fragment() {

    private var _binding: AlbumCreateCommentFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: AlbumsAdapter? = null

    private val viewModel: AlbumCommentViewModel by lazy {
        ViewModelProvider(this).get(AlbumCommentViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val args: CreateCommentAlbumFragmentArgs by navArgs()
        Log.d("Álbum seleccionado", args.albumId.toString())
        // Inflate the layout for this fragment
        _binding = AlbumCreateCommentFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(this, AlbumCommentViewModel.Factory(application, args.albumId)).get(AlbumCommentViewModel::class.java)
        _binding!!.albumCommentFormButton?.setOnClickListener{

            val rating : RatingBar  = binding.albumCommentRatingBar
            val comment = binding.albumCommentEditText.text
            val idUser = args.albumId


            viewModel.createCommentAlbum(comment.toString(),rating.getRating().toInt(),
                Collector(1)
            )
            /*println("Ranking: " + rating.getRating())
            println("Comentario: " + comment)
            println("Comentario: " + idUser)*/

        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}