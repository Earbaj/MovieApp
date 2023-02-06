package com.example.movieapp.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentDetailsBinding


/*
 * This Fragment shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the DetailViewModel, which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */
class DetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        val bindings = FragmentDetailsBinding.inflate(inflater)
        // Giving the binding access to the OverviewViewModel
        bindings.lifecycleOwner = this
        val movieProperty = DetailsFragmentArgs.fromBundle(requireArguments()).movieProparty
        val viewModelFactory = DetailViewModelFactory(movieProperty, application)
        bindings.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)
        return bindings.root
    }
}