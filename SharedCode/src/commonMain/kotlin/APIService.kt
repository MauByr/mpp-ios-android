package com.jetbrains.handson.mpp.mobile

import com.jetbrains.handson.mpp.mobile.dataObjects.FareResponse
import com.jetbrains.handson.mpp.mobile.dataObjects.StationList
import com.soywiz.klock.*
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.core.*


object APIService {
    private object APIRoutes {
        const val STATIONS = "stations/"
    }

    const val rootURL = "https://mobile-api-softwire2.lner.co.uk/v1/"
    private fun httpClient():HttpClient = HttpClient { install(JsonFeature) }

    suspend fun getStationList(): StationList? {
        return  makeWebRequest(
                Url(rootURL + APIRoutes.STATIONS), StationList(emptyList())
            )

    }

    suspend fun getJourneyList(query: JourneyQuery): FareResponse? {
        return makeWebRequest<FareResponse?>(query.getUrl(),null)
    }

    private suspend inline fun <reified T> makeWebRequest(url: Url, default: T): T? {
        return try {
            httpClient().use { it.get<T>(url)}
        } catch (e: Exception) {
            println(e.message)
            default
        }
    }
}

class JourneyQuery(private val from: String, private val to: String, private val time: DateTimeTz = DateTime.nowLocal()
) {

    fun getUrl(): Url {
        val builder = URLBuilder(APIService.rootURL +"fares")
        with(builder) {
            parameters["originStation"] = from
            parameters["destinationStation"] = to
            parameters["outboundDateTime"] = calcSearchTime().format("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            parameters["numberOfAdults"] = "1"
            parameters["numberOfChildren"] = "0"
        }
        return builder.build()
    }
    private fun calcSearchTime():DateTimeTz{
        val timeOffset = TimeSpan(60000.0)
        val timeDiff = time - DateTime.nowLocal()
        if(timeDiff<timeOffset){
            return time.addOffset(timeOffset-timeDiff)
        }
        return time
    }
}

