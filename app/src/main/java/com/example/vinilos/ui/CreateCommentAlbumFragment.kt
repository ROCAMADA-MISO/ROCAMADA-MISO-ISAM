package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumCreateCommentFragmentBinding
import com.example.vinilos.databinding.AlbumDetailFragmentBinding
import com.example.vinilos.viewmodels.AlbumCommentViewModel
import com.example.vinilos.viewmodels.AlbumDetailViewModel
import com.example.vinyls_jetpack_application.ui.adapters.AlbumsAdapter


class CreateCommentAlbumFragment : Fragment() {

    private var _binding: AlbumCreateCommentFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumCommentViewModel
    private var viewModelAdapter: AlbumsAdapter? = null

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
        //binding.viewModel = ViewModelProvider(this, AlbumCommentViewModel.Factory(application, args.albumId)).get(AlbumCommentViewModel::class.java)
        //return inflater.inflate(R.layout.album_create_comment_fragment, container, false)
        return view
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateCommentAlbumFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}