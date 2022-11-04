package com.example.vinilos.prizes.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentCreateAwardBinding
import com.example.vinilos.prizes.network.Prize

/**
 * This fragment shows the the status of the Artist web services transaction.
 */
class PrizesOverviewFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: PrizesOverviewViewModel by lazy {
        ViewModelProvider(this).get(PrizesOverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        val binding = DataBindingUtil.inflate<FragmentCreatePrizeBinding>(
//            inflater, R.layout.fragment_create_award, container, false)
        val binding = FragmentCreateAwardBinding.inflate(inflater)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val awardOrganization = binding.awardOrganizationEditText.text
            val awardNameEdit = binding.awardNameEditText.text
            val awardDescriptionEdit = binding.awardDescriptionEditText.text
            Log.d("awardOrganization", awardOrganization.toString())
            Log.d("awardNameEdit", awardNameEdit.toString())
            Log.d("awardDescriptionEdit", awardDescriptionEdit.toString())
            val newPrize =
                Prize(name = awardNameEdit.toString(),
                    organization = awardOrganization.toString(),
                    description = awardDescriptionEdit.toString())
            viewModel.createPrize(newPrize)
        }



        return binding.root
    }

}
