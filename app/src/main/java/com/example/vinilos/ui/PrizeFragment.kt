package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.PrizeFragmentBinding
import com.example.vinilos.models.Prize
import com.example.vinilos.ui.adapters.PrizesAdapter
import com.example.vinilos.viewmodels.PrizeViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PrizeFragment : Fragment() {
    private var _binding: PrizeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PrizeViewModel
    private var viewModelAdapter: PrizesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PrizeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        _binding!!.prizeFormButton?.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_prizeFragment_to_createPrizeFragment))
        viewModelAdapter = PrizesAdapter()
        return view
    }

    companion object {
        fun newInstance() = PrizeFragment()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.prizesRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

        activity?.actionBar?.title = getString(R.string.title_prizes)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_prizes)
        viewModel = ViewModelProvider(this, PrizeViewModel.Factory(activity.application)).get(PrizeViewModel::class.java)
        viewModel.prizes.observe(viewLifecycleOwner, Observer<List<Prize>> {
            it.apply {
                viewModelAdapter!!.prizes = this
                if(this.isEmpty()){
                    binding.txtNoPrizes.visibility = View.VISIBLE
                }else{
                    binding.txtNoPrizes.visibility = View.GONE
                }
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