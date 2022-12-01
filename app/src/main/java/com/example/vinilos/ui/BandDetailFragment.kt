package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.BandDetailFragmentBinding
import com.example.vinilos.models.Albums
import com.example.vinilos.viewmodels.BandDetailViewModel
import com.example.vinyls_jetpack_application.ui.adapters.ArtistAlbumsAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BandDetailFragment : Fragment() {
    private var _binding: BandDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BandDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: ArtistAlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val args: BandDetailFragmentArgs by navArgs()
        Log.d("Banda seleccionada", args.bandId.toString())
        _binding = BandDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(this, BandDetailViewModel.Factory(application, args.bandId)).get(BandDetailViewModel::class.java)
        viewModelAdapter = ArtistAlbumsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistalbumsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val args: BandDetailFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this, BandDetailViewModel.Factory(activity.application,args.bandId)).get(BandDetailViewModel::class.java)
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