package com.sulavtimsina.jetpackdemo.ui.screen1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sulavtimsina.jetpackdemo.R
import com.sulavtimsina.jetpackdemo.databinding.FragmentItemListBinding
import com.sulavtimsina.jetpackdemo.databinding.ItemListContentBinding
import com.sulavtimsina.jetpackdemo.ui.screen1.placeholder.LaunchListItemContent

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.itemList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        setupRecyclerView(recyclerView, itemDetailFragmentContainer)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?
    ) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            LaunchListItemContent.ITEMS, itemDetailFragmentContainer
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: List<LaunchListItemContent.LaunchListItem>,
        private val itemDetailFragmentContainer: View?
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.tvMissionName.text = item.missionName
            holder.tvRocketName.text = item.rocketName

            with(holder.itemView) {
                tag = item
                setOnClickListener { itemView ->
                    val item = itemView.tag as LaunchListItemContent.LaunchListItem
                    val bundle = Bundle()
                    bundle.putString(
                        ItemDetailFragment.ARG_ITEM_ID,
                        item.missionName
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_item_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    }
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val tvMissionName: TextView = binding.tvMissionName
            val tvRocketName: TextView = binding.tvRocketName
            val siteName: TextView = binding.siteName
            val launchDate: TextView = binding.launchDate
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}