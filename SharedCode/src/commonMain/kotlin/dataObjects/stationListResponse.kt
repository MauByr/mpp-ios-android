package com.jetbrains.handson.mpp.mobile.dataObjects

import io.ktor.utils.io.pool.*
import kotlinx.serialization.Serializable

typealias NLC  = String
typealias CRS  = String
@Serializable
data class StationList(
    val stations:List<StationInfo>
)
@Serializable
data class StationInfo(
    val id:Int?,
    val name:String?,
    val aliases:List<String>?,
    val crs: CRS?,
    val  nlc : NLC?,
    val latitude : Double?,
    val longitude : Double?,
    val isGroupStation : Boolean?,
    val isSilverSeekStation:Boolean?

)
@Serializable
data class FareResponse(
    val numberOfAdults : Int,
    val numberOfChildren:Int,
    val outboundJourneys: Int
)