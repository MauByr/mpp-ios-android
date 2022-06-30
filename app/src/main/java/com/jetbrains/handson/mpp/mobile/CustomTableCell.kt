package com.jetbrains.handson.mpp.mobile

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem

class CustomTableCell(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun fillInfo(journey: JourneyTableDataElem) {
        with(itemView) {
            findViewById<TextView>(R.id.carrier).text = journey.trainOperator
            findViewById<TextView>(R.id.depStation).text = journey.startStation.shortName
            findViewById<TextView>(R.id.depTime).text = journey.getStartTime()
            findViewById<TextView>(R.id.arrStation).text = journey.endStation.shortName
            findViewById<TextView>(R.id.arrTime).text = journey.getArrivalTime()
            findViewById<TextView>(R.id.price).text = journey.getPrice()
            findViewById<TextView>(R.id.journeyTime).text = journey.getJourneyTime()
        }
    }
}