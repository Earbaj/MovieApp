package com.example.movieapp.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentDetailsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application
        val bindings = FragmentDetailsBinding.inflate(inflater)
        bindings.lifecycleOwner = this
        val movieProperty = DetailsFragmentArgs.fromBundle(requireArguments()).movieProparty
        val viewModelFactory = DetailViewModelFactory(movieProperty, application)
        bindings.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)
        return bindings.root
    }
}