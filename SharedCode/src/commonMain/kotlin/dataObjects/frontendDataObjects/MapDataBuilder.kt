package com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects

import com.jetbrains.handson.mpp.mobile.dataObjects.JourneyAPIObjects
import com.jetbrains.handson.mpp.mobile.dataObjects.StationInfo

class MapDataBuilder(
    private val stationDataMap: Map<String, StationInfo>
) {
    fun createMapDataFromAPIResponse(journey: JourneyAPIObjects): MapData {
        val legs =
            journey.legs.map { leg -> Leg(leg.callingPoints.map { stationCodeToJourneyWayPoint(it.station.crs) }) }
        val start = stationCodeToJourneyWayPoint(journey.origin.crs)
        val end = stationCodeToJourneyWayPoint(journey.origin.crs)
        return MapData(
            start,
            end,
            legs
        )
    }

    private fun stationCodeToJourneyWayPoint(crs: String): JourneyWayPoint {
        val station = stationDataMap[crs]
        return JourneyWayPoint(
            station?.name ?: "OOPS",
            GeoCoordinate(
                station?.latitude ?: 0.0,
                station?.longitude ?: 0.0
            )
        )
    }
}