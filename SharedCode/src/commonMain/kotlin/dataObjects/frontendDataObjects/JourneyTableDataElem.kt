package com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects

import com.jetbrains.handson.mpp.mobile.dataObjects.Journey
import com.jetbrains.handson.mpp.mobile.dataObjects.Station
import com.jetbrains.handson.mpp.mobile.dataObjects.StationInfo
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.parse
import com.soywiz.klock.*

const val BEST_TRAIN_OPERATOR = "London North Eastern Railway"
val ISODateFormat = DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

class JourneyTableDataElem(journey: Journey) {
    val startStation: JourneyStation = JourneyStation(journey.originStation)

    private val startTimeRaw: DateTimeTz = ISODateFormat.parse(journey.departureTime)
    val startTime = DateFormat("HH:mm").format(startTimeRaw)

    val endStation: JourneyStation = JourneyStation(journey.destinationStation)

    private val endTimeRaw: DateTimeTz = ISODateFormat.parse(journey.arrivalTime)
    val endTime = DateFormat("HH:mm").format(endTimeRaw)

    private val journeyTimeMins: Int = journey.journeyDurationInMinutes.toInt()
    val journeyTime = "${journeyTimeMins / 60}:${journeyTimeMins % 60}"

    val changes: Int = (journey.legs.size - 1)

    private val price: Int? =
        (journey.tickets.minBy { it.priceInPennies }?.priceInPennies)
    val ticketCost = if (price == null) "Unavailable" else "£${price / 100}.${price % 100}"

    val trainOperator: String = journey.primaryTrainOperator.name
    fun isAGoodTrain() = (trainOperator == BEST_TRAIN_OPERATOR)

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use ticketCost Property instead")
    fun getPrice(): String {
        if (price == null) {
            return "Unavailable"
        }
        return "£${price / 100}.${price % 100}"
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use startTime Property instead")
    fun getStartTime(): String {
        return DateFormat("HH:mm").format(startTimeRaw)
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use endTime Property instead")
    fun getArrivalTime(): String {
        return DateFormat("HH:mm").format(endTimeRaw)
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use journeyTime Property instead")
    fun getJourneyTime(): String {
        return "${journeyTimeMins / 60}:${journeyTimeMins % 60}"
    }

}

class JourneyStation(station: Station) {
    constructor(stationInfo: StationInfo) : this(
        Station(
            stationInfo.name ?: "Unknown",
            stationInfo.crs ?: "",
            stationInfo.nlc ?: "000"
        )
    )

    val shortName: String = station.displayName.replace("[aeiou]".toRegex(), "")
    val fullName: String = station.displayName
    val crsCode: String = station.crs
    override fun toString(): String {
        return fullName
    }
}
