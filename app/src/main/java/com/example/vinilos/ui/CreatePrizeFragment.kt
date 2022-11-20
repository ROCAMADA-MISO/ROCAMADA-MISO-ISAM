package com.example.vinilos.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.PrizeCreateFragmentBinding
import com.example.vinilos.models.Prize
import com.example.vinilos.ui.adapters.PrizesAdapter
import com.example.vinilos.viewmodels.CreatePrizeViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CreatePrizeFragment : Fragment() {
    private var _binding: PrizeCreateFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: PrizesAdapter? = null

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: CreatePrizeViewModel by lazy {
        ViewModelProvider(this).get(CreatePrizeViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PrizeCreateFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        _binding!!.createPrizeButton?.setOnClickListener{
            val awardOrganization = binding.awardOrganizationEditText.text
            val awardNameEdit = binding.awardNameEditText.text
            val awardDescriptionEdit = binding.awardDescriptionEditText.text
            viewModel.createPrize(awardNameEdit.toString(),awardOrganization.toString(),awardDescriptionEdit.toString())
            Navigation.createNavigateOnClickListener(R.id.action_createPrizeFragment_to_prizeFragment)
        }
        viewModelAdapter = PrizesAdapter()
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