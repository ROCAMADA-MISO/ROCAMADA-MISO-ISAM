package com.example.vinilos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.PrizeFragmentBinding
import com.example.vinilos.ui.adapters.PrizesAdapter
import com.example.vinilos.viewmodels.PrizeViewModel

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_NAME = "name"
private const val ARG_ORGANIZATION = "organization"
private const val ARG_DESCRIPTION = "description"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateCommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreatePrizeFragment : Fragment() {
    private var _binding: PrizeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PrizeViewModel
    private var viewModelAdapter: PrizesAdapter? = null

    private var name: String? = null
    private var organization: String? = null
    private var description: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            organization = it.getString(ARG_ORGANIZATION)
            description = it.getString(ARG_DESCRIPTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_prize, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name Prize name.
         * @param organization Prize organization.
         * @param description Prize description.
         * @return A new instance of fragment CreateCommentFragment.
         */
        @JvmStatic
        fun newInstance(name: String, organization: String, description:String) =
            CreatePrizeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_ORGANIZATION, organization)
                    putString(ARG_DESCRIPTION, description)
                }
            }
    }
}