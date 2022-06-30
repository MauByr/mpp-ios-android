package com.jetbrains.handson.mpp.mobile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem

class SearchResultAdapter (private val dataSet:List<JourneyTableDataElem>) : RecyclerView.Adapter<CustomTableCell>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomTableCell {
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.journey_info_cell,parent,false)
        return CustomTableCell(view)
    }

    override fun onBindViewHolder(holder: CustomTableCell, position: Int) {
        holder.fillInfo(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size


}