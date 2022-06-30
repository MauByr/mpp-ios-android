package com.jetbrains.handson.mpp.mobile

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem

class CustomTableCell(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun fillInfo(journey: JourneyTableDataElem) {
        this.itemView.findViewById<TextView>(R.id.carrier).text = journey.trainOperator
        this.itemView.findViewById<TextView>(R.id.depStation).text = journey.startStation.shortName
        this.itemView.findViewById<TextView>(R.id.depTime).text = journey.getStartTime()
        this.itemView.findViewById<TextView>(R.id.arrStation).text = journey.endStation.shortName
        this.itemView.findViewById<TextView>(R.id.arrTime).text = journey.getArrivalTime()
        this.itemView.findViewById<TextView>(R.id.price).text = journey.getPrice()
        this.itemView.findViewById<TextView>(R.id.journeyTime).text = journey.getJourneyTime()

    }

}