package com.example.movieapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.adapter.PhotoGridAdapter
import com.example.movieapp.databinding.FragmentOverviewBinding


/**
 * A simple [Fragment] subclass.
 * Use the [OverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OverviewFragment : Fragment() {

    //Lazily initialize our OverviewViewModel
    private val viewModel: OverViewViewModel by lazy {
        ViewModelProvider(this).get(OverViewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bindings = FragmentOverviewBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        bindings.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        bindings.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        bindings.photosGrid.adapter = PhotoGridAdapter(
            PhotoGridAdapter.OnClickListener(){
                viewModel.displayPropertyDetails(it)
            }
        )
        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(it != null){
                findNavController()
                    .navigate(OverviewFragmentDirections.actionOverviewFragment2ToDetailsFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })
        return bindings.root
    }

}