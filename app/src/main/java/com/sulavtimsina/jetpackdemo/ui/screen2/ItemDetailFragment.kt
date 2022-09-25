package com.sulavtimsina.jetpackdemo.ui.screen2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.sulavtimsina.jetpackdemo.R
import com.sulavtimsina.jetpackdemo.databinding.FragmentItemDetailBinding
import com.sulavtimsina.jetpackdemo.util.Resource
import com.sulavtimsina.jetpackdemo.viewmodels.RocketLaunchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {


    lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null
    val navArgs by navArgs<ItemDetailFragmentArgs>()
    private val viewModel: RocketLaunchesViewModel by viewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        itemDetailTextView = binding.itemDetail
        observeSingleLaunchList()
        return rootView
    }


    private fun observeSingleLaunchList(){
        lifecycleScope.launchWhenCreated {
            viewModel.getSingleLaunchList(requireActivity(),navArgs.flightNumber)
        }
        viewModel.singleLaunchList.observe(viewLifecycleOwner){ responseResource ->
            when (responseResource) {
                is Resource.Success->{

                    binding.progressBar!!.isVisible = false
                    responseResource.data?.let {

                       itemDetailTextView.text = it.launch_site.site_name

                    }
                }


                is Resource.Loading->{
                    binding.progressBar!!.isVisible=true

                }
                is Resource.Error->{
                    binding.progressBar!!.isVisible=false
                    Toast.makeText(requireActivity(), R.string.error_message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}