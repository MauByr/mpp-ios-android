package com.jetbrains.handson.mpp.mobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem

class SearchResultAdapter(private val dataSet: List<JourneyTableDataElem>) :
    RecyclerView.Adapter<TicketInfoCell>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketInfoCell {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.journey_info_cell, parent, false)
        return TicketInfoCell(view)
    }

    override fun onBindViewHolder(holder: TicketInfoCell, position: Int) {
        holder.fillInfo(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size


}