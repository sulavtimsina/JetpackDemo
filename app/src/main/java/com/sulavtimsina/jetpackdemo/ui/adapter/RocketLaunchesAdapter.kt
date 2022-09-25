package com.sulavtimsina.jetpackdemo.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sulavtimsina.jetpackdemo.R
import com.sulavtimsina.jetpackdemo.data.remote.model.LaunchItem
import com.sulavtimsina.jetpackdemo.databinding.ItemListContentBinding
import com.sulavtimsina.jetpackdemo.ui.interfaces.ItemClickListener
import java.text.SimpleDateFormat
import java.util.*

class RocketLaunchesAdapter : RecyclerView.Adapter<RocketLaunchesAdapter.ViewHolder>() {
    var itemClickListener: ItemClickListener<LaunchItem>? = null
    private val launchList = mutableListOf<LaunchItem>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = launchList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = ItemListContentBinding.bind(holder.itemView)
        val launchItem = launchList[position]
        binding.tvMissionName.text = launchItem.mission_name
        //iamge logo
        binding.tvRocketName.text = launchItem.rocket.rocket_name
        binding.siteName.text = launchItem.launch_site.site_name
        binding.launchDate.text = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(launchItem.launch_date_unix*1000L)


            Glide.with(holder.itemView.context).load(launchItem.links.mission_patch_small).placeholder(
                R.mipmap.ic_mission_patch_placeholder)
                .into(binding.ivThumbnail)



        binding.root.setOnClickListener {
            itemClickListener?.onItemClick(launchItem)
        }
    }

    fun updateDataSet(newList: List<LaunchItem>) {
        launchList.clear()
        launchList.addAll(newList)
        notifyDataSetChanged()
        //   initObservers()
    }
}