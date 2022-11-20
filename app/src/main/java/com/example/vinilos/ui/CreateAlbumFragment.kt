package com.example.vinilos.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumCreateFragmentBinding
import com.example.vinilos.databinding.PrizeCreateFragmentBinding
import com.example.vinilos.ui.adapters.PrizesAdapter
import com.example.vinilos.viewmodels.CreateAlbumViewModel
import com.example.vinilos.viewmodels.CreatePrizeViewModel
import com.example.vinyls_jetpack_application.ui.adapters.AlbumsAdapter



/**
 * A simple [Fragment] subclass.
 * Use the [CreateAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAlbumFragment : Fragment() {
    private var _binding: AlbumCreateFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: AlbumsAdapter? = null

    private val viewModel: CreateAlbumViewModel by lazy {
        ViewModelProvider(this).get(CreateAlbumViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumCreateFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        _binding!!.createAlbumButton?.setOnClickListener{

            val postResultTextView : TextView = binding.postResultText

            val name = binding.AlbumNameEditText.text
            val cover = binding.AlbumCoverEditText.text
            val releaseDate = binding.AlbumReleaseDateEditText.text
            val description = binding.AlbumDescriptionEditText.text
            val genre = binding.AlbumGenreEditText.text
            val recordLabel = binding.AlbumRecordLabelEditText.text
            viewModel.createAlbum(name.toString(),cover.toString(),releaseDate.toString(),description.toString(),genre.toString(),recordLabel.toString() )
            postResultTextView.text = "Creacion Exitosa"
            Navigation.createNavigateOnClickListener(R.id.action_createAlbumFragment_to_albumFragment)

        }

        viewModelAdapter = AlbumsAdapter()
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