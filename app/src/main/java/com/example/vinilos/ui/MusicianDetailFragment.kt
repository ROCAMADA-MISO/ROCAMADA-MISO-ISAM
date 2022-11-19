package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.vinilos.databinding.MusicianDetailFragmentBinding
import com.example.vinilos.viewmodels.MusicianDetailViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MusicianDetailFragment : Fragment() {
    private var _binding: MusicianDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MusicianDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val args: MusicianDetailFragmentArgs by navArgs()
        Log.d("Musico seleccionado", args.musicianId.toString())
        _binding = MusicianDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(this, MusicianDetailViewModel.Factory(application, args.musicianId)).get(MusicianDetailViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = "Musico"
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