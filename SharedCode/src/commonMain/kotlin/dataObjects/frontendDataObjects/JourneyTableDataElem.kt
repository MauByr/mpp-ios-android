package com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects

import com.jetbrains.handson.mpp.mobile.dataObjects.Journey
import com.jetbrains.handson.mpp.mobile.dataObjects.Station
import com.soywiz.klock.*

val ISODate = ISO8601.DATETIME_COMPLETE
const val BEST_TRAIN_OPERATOR = "LNER"
class JourneyTableDataElem(journey: Journey) {
    val startStation: JourneyStation = JourneyStation(journey.originStation)
    private val startTime: DateTimeTz =
        ISODate.parse(journey.departureTime)// dateFormat.parse(journey.departureTime)
    val endStation: JourneyStation = JourneyStation(journey.destinationStation)
    private val endTime: DateTimeTz = ISODate.parse(journey.arrivalTime)
    private val journeyTime:Int = journey.journeyDurationInMinutes.toInt()
    val changes: Int = (journey.legs.size - 1)
    private val price: Int =
        (journey.tickets.minBy { it.priceInPennies }?.priceInPennies ?: 10000000)
    val trainOperator: String = journey.primaryTrainOperator.name
    fun isAGoodTrain() = (trainOperator == BEST_TRAIN_OPERATOR)

    fun getPrice():String{
        return "£${price/100}.${price%100}"
    }
    fun getStartTime():String{
        return DateFormat("HH:SS").format(startTime)
    }
    fun getArrivalTime():String{
        return DateFormat("HH:SS").format(endTime)
    }
    fun getJouneyTime():String{
        return "${price/60}:${price%60}"
    }

}

class JourneyStation(station: Station) {
    val shortName: String = station.displayName.replace(Regex.fromLiteral("[aeiou]"), "")
    val fullName: String = station.displayName
    val crsCode: String = station.crs
}
