package com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects

import com.jetbrains.handson.mpp.mobile.dataObjects.Journey
import com.jetbrains.handson.mpp.mobile.dataObjects.Station
import com.soywiz.klock.*

const val BEST_TRAIN_OPERATOR = "LNER"
val ISODateFormat = DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
class JourneyTableDataElem(journey: Journey) {
    val startStation: JourneyStation = JourneyStation(journey.originStation)
    private val startTime: DateTimeTz =
        ISODateFormat.parse(journey.departureTime)
    val endStation: JourneyStation = JourneyStation(journey.destinationStation)
    private val endTime: DateTimeTz = ISODateFormat.parse(journey.arrivalTime)
    private val journeyTime: Int = journey.journeyDurationInMinutes.toInt()
    val changes: Int = (journey.legs.size - 1)
    private val price: Int =
        (journey.tickets.minBy { it.priceInPennies }?.priceInPennies ?: 10000000)
    val trainOperator: String = journey.primaryTrainOperator.name
    fun isAGoodTrain() = (trainOperator == BEST_TRAIN_OPERATOR)
    fun getPrice(): String {
        return "£${price / 100}.${price % 100}"
    }

    fun getStartTime(): String {
        return DateFormat("HH:mm").format(startTime)
    }

    fun getArrivalTime(): String {
        return DateFormat("HH:mm").format(endTime)
    }

    fun getJourneyTime(): String {
        return "${journeyTime / 60}:${journeyTime % 60}"
    }

}

class JourneyStation(station: Station) {
    val shortName: String = station.displayName.replace("[aeiou]".toRegex(), "")
    val fullName: String = station.displayName
    val crsCode: String = station.crs
}
