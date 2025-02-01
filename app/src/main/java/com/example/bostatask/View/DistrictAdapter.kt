package com.example.bostatask.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bostatask.Model.District
import com.example.bostatask.databinding.DistrictItemBinding


class DistrictAdapter : RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>() {

    private var districtList: List<District> = listOf()

    inner class DistrictViewHolder(val binding: DistrictItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val binding = DistrictItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        val district = districtList[position]
        holder.binding.districtName.text = district.districtName
        holder.binding.uncoveredTag.visibility = if (district.dropOffAvailability) View.GONE else View.VISIBLE
    }

    override fun getItemCount(): Int = districtList.size

    fun updateList(newList: List<District>) {

        districtList = newList
        notifyDataSetChanged()
    }
}