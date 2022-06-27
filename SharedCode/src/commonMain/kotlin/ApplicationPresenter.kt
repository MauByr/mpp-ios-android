package com.jetbrains.handson.mpp.mobile

import com.jetbrains.handson.mpp.mobile.dataObjects.StationInfo
import com.jetbrains.handson.mpp.mobile.dataObjects.StationList
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlinx.serialization.*

import kotlin.coroutines.CoroutineContext


const val rootURL  = "https://mobile-api-softwire1.lner.co.uk/v1/stations"
class ApplicationPresenter: ApplicationContract.Presenter() {

    private val dispatchers = AppDispatchersImpl()
    private var view: ApplicationContract.View? = null
    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job

    override fun onViewTaken(view: ApplicationContract.View) {
        this.view = view
        view.setLabel(createApplicationScreenMessage()+"!!!!")
        launch { getStationList() }
    }

    override fun onSearchClicked() {
//        view?.setLabel("CLIK")
        this.view?.showResults("result tbd")
    }

    suspend fun getStationList() {
        try {


        val req  = HttpClient(){
            install(JsonFeature)
        }.get<StationList>(
            "https://mobile-api-softwire2.lner.co.uk/v1/stations")
        val stationNames = req.stations.mapNotNull { it.name }
            view?.populateStationList(stationNames)
            println(req)
        }
        catch (e:Exception){
            println(e)
        }
    }

}
