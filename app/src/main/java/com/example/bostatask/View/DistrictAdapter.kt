package com.example.bostatask.View

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bostatask.Model.District
import com.example.bostatask.databinding.DistrictItemBinding

class DistrictDiff : DiffUtil.ItemCallback<District>() {
    override fun areItemsTheSame(oldItem: District, newItem: District): Boolean {
       return oldItem.districtId==newItem.districtId
    }

    override fun areContentsTheSame(oldItem: District, newItem: District): Boolean {
       return oldItem == newItem
    }
}

class DistrictAdapter : ListAdapter<District, DistrictAdapter.DistrictViewHolder>(DistrictDiff()) {

    lateinit var binding: DistrictItemBinding
    class DistrictViewHolder(val binding: DistrictItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= DistrictItemBinding.inflate(inflater, parent, false)
        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        var district = getItem(position)
        holder.binding.districtName.text = district.districtName
        holder.binding.uncoveredTag.visibility = if (district.dropOffAvailability) RecyclerView.VISIBLE else RecyclerView.GONE
    }
}