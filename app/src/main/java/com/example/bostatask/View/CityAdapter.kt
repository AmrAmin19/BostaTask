package com.example.bostatask.View

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bostatask.Model.City
import com.example.bostatask.R
import com.example.bostatask.databinding.CityItemBinding

class CityDiff:DiffUtil.ItemCallback<City>()
{
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
    return oldItem.cityId == newItem.cityId
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
       return oldItem == newItem
    }

}

class CityAdapter:ListAdapter<City, CityAdapter.CityViewHolder>(CityDiff())
{


    lateinit var binding: CityItemBinding
    lateinit var adapter: DistrictAdapter

    class CityViewHolder(val binding: CityItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= CityItemBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        var city = getItem(position)
        holder.binding.cityName.text = city.cityName

        // test the nested
        holder.binding.districtRecycler.layoutManager= LinearLayoutManager(holder.itemView.context)
        adapter = DistrictAdapter()
        holder.binding.districtRecycler.adapter = adapter
        adapter.submitList(city.districts)

        // colapse tech test

        if (city.isExpanded) {
            holder.binding.districtContainer.visibility = RecyclerView.VISIBLE
            holder.binding.arrowIcon.setImageResource(R.drawable.baseline_arrow_upward_24)
        } else {
            holder.binding.districtContainer.visibility = RecyclerView.GONE
            holder.binding.arrowIcon.setImageResource(R.drawable.baseline_arrow_downward_24)
        }

        // Toggle expansion when arrow is clicked
        holder.binding.arrowIcon.setOnClickListener {
            city.isExpanded = !city.isExpanded
            notifyItemChanged(position) // Update only the clicked item
        }

    }

}