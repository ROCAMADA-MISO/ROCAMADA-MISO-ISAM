package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AssociateAlbumFragmentBinding
import com.example.vinilos.models.Albums
import com.example.vinilos.viewmodels.AlbumsViewModel
import com.example.vinyls_jetpack_application.ui.adapters.AssociateAlbumAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [AssociateAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AssociateAlbumFragment : Fragment() {
    private var _binding: AssociateAlbumFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumsViewModel
    private var viewModelAdapter: AssociateAlbumAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AssociateAlbumFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModelAdapter = AssociateAlbumAdapter()
        binding.albumFormButton.setOnClickListener {
            Log.d("albumFormButton", viewModelAdapter!!.selectedItemId.toString())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, AlbumsViewModel.Factory(activity.application)).get(AlbumsViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Albums>> {
            it.apply {
                viewModelAdapter!!.albums = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
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