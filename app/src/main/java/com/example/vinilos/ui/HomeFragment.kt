package com.example.vinilos

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.vinilos.databinding.HomeFragmentBinding


/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: HomeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment,container,false)
        binding.bandListButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_bandFragment))
        binding.musicianListButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_musicianFragment))
        binding.albumsListButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_albumsFragment))
        binding.prizesList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_prizeFragment))

        return binding.root
    }

}