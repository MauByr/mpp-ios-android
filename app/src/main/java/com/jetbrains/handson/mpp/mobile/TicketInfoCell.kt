package com.jetbrains.handson.mpp.mobile

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem
import kotlinx.android.synthetic.main.journey_info_cell.view.*

class TicketInfoCell(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun fillInfo(journey: JourneyTableDataElem) {
        with(itemView) {
            carrier.text = journey.trainOperator
            depStation.text = journey.startStation.shortName
            depTime.text = journey.getStartTime()
            arrStation.text = journey.endStation.shortName
            arrTime.text = journey.getArrivalTime()
            price.text = journey.getPrice()
            journeyTime.text = journey.getJourneyTime()
        }
    }
}