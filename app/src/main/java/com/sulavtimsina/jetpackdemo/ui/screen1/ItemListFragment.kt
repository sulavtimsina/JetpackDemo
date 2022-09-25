package com.sulavtimsina.jetpackdemo.ui.screen1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sulavtimsina.jetpackdemo.R
import com.sulavtimsina.jetpackdemo.data.remote.model.LaunchItem
import com.sulavtimsina.jetpackdemo.databinding.FragmentItemListBinding
import com.sulavtimsina.jetpackdemo.ui.adapter.RocketLaunchesAdapter
import com.sulavtimsina.jetpackdemo.ui.interfaces.ItemClickListener
import com.sulavtimsina.jetpackdemo.util.Resource
import com.sulavtimsina.jetpackdemo.viewmodels.RocketLaunchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment() {
    private val viewModel: RocketLaunchesViewModel by viewModels()
    private lateinit var searchAdapter: RocketLaunchesAdapter


    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = RocketLaunchesAdapter()
        searchAdapter.itemClickListener = object : ItemClickListener<LaunchItem> {
            override fun onItemClick(item: LaunchItem) {



                    //go to the results page
                    findNavController().navigate(
                        ItemListFragmentDirections.showItemDetail(item.flight_number)
                    )
                }

        }
        binding.itemList.layoutManager = LinearLayoutManager(requireContext())
        binding.itemList.adapter = searchAdapter

       observeLaunchList()
    }



    private fun observeLaunchList(){
        lifecycleScope.launchWhenCreated {
            viewModel.getLaunchList(requireActivity())
        }
        viewModel.launchList.observe(viewLifecycleOwner){ responseResource ->
            when (responseResource) {
                is Resource.Success->{

                    binding.progressBar!!.isVisible = false
                    responseResource.data?.let {

                       searchAdapter.updateDataSet(it)

                        }
                    }


                is Resource.Loading->{
                    binding.progressBar!!.isVisible=true

                }
                is Resource.Error->{
                    binding.progressBar!!.isVisible=false
                    Toast.makeText(requireActivity(),getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}