package com.jetbrains.handson.mpp.mobile.dataObjects

import kotlinx.serialization.Serializable

@Serializable
data class JourneyAPIObjects(
    val origin: Station,
    val destination: Station,
    val legs: List<JourneyLeg>
)

@Serializable
data class JourneyLeg(
    val legId: String,
    val origin: Station,
    val callingPoints: List<StationPass>
)

@Serializable
data class StationPass(
    val station: Station,
    val arrivalDateTime: String,
    val arrivalRealTime: String? = null,
    val departureDateTime: String? = null,
    val departureRealTime: String? = null,
    val status: String? = null,
    val stopType: String? = null
)
