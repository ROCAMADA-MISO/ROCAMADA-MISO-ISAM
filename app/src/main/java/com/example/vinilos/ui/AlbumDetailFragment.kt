package com.example.vinilos.ui

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.vinilos.viewmodels.AlbumDetailViewModel
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumDetailFragmentBinding
import com.example.vinilos.ui.adapters.AlbumsCommentAdapter
import com.example.vinyls_jetpack_application.ui.adapters.AlbumsAdapter


class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumDetailViewModel
    private var viewModelAdapter: AlbumsCommentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val args: AlbumDetailFragmentArgs by navArgs()
        Log.d("Álbum seleccionado", args.albumId.toString())
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory(application, args.albumId)).get(AlbumDetailViewModel::class.java)
        _binding!!.albumAddcommentFormButton?.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_albumDetailFragment_to_albumCommentFragment))

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = "Álbum"
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