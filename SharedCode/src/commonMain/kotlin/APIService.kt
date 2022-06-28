package com.jetbrains.handson.mpp.mobile

import com.jetbrains.handson.mpp.mobile.dataObjects.DateTimeString
import com.jetbrains.handson.mpp.mobile.dataObjects.FareResponse
import com.jetbrains.handson.mpp.mobile.dataObjects.StationInfo
import com.jetbrains.handson.mpp.mobile.dataObjects.StationList
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*


class APIService {
    private fun createHTTPClient():HttpClient = HttpClient(){
        install(JsonFeature)
    }
    suspend fun getStationList() :StationList{
        return try {
            createHTTPClient().get(
                "https://mobile-api-softwire2.lner.co.uk/v1/stations")
        } catch (e :Exception){
            StationList(emptyList())
        }
    }
    suspend fun getJourneyList(query:JourneyQuery):FareResponse?{
        return try {
            createHTTPClient().get(
                query.getUrl()
            )
        } catch (e :Exception){
            println(e.message)
            null
        }
    }



}
class JourneyQuery (val from:String,val to:String,val time:DateTime? = null) {

     fun getUrl(): Url {
         val builder = URLBuilder("https://mobile-api-softwire2.lner.co.uk/v1/fares")
            with(builder){
                parameters["originStation"] = from
                parameters["destinationStation"] = to
                parameters["outboundDateTime"] = getNowAsString()
                parameters["numberOfAdults"] = "1"
                parameters["numberOfChildren"] = "0"
            }
         return builder.build()
    }

    private fun getNowAsString(): String {
        return "2022-07-07T12:16:27.371+00:00"
//        return DateTime.nowLocal().
    }
}