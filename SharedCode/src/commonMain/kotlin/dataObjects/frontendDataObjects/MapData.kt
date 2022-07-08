package com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects

data class MapData(
    val startStation: JourneyWayPoint,
    val endStation: JourneyWayPoint
)

data class JourneyWayPoint(
    val name: String,
    val coords: GeoCoordinate
)

data class GeoCoordinate(
    val latitude: Double,
    val longitude: Double
)

fun getTestMapData(): MapData {
    return MapData(
        JourneyWayPoint(
            "London",
            GeoCoordinate(51.0, 00.0)
        ),
        JourneyWayPoint(
            "Edinburgh",
            GeoCoordinate(65.0, 00.0)
        )
    )
}