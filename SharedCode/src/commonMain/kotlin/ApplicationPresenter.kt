package com.jetbrains.handson.mpp.mobile

import com.jetbrains.handson.mpp.mobile.dataObjects.StationInfo
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyStation
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.MapDataBuilder
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ApplicationPresenter : ApplicationContract.Presenter() {

    private val dispatchers = AppDispatchersImpl()
    private var view: ApplicationContract.View? = null
    private val job: Job = SupervisorJob()
    private val stationMap = HashMap<String, StationInfo>()


    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job

    override fun onViewTaken(view: ApplicationContract.View) {
        this.view = view
        view.setLabel(createApplicationScreenMessage())
        getStationList()
    }

    override fun onSearchClicked(
        initialStation: String,
        ultimateStation: String,
        timeUTCString: String? //TODO: implement later
    ) {
        launch {
            APIService.getJourneyList(JourneyQuery(initialStation, ultimateStation))
                ?.let { journey ->
                    println(journey)
                    view?.showResults(journey.outboundJourneys.map { elem ->
                        JourneyTableDataElem(
                            elem
                        )
                    })
                } ?: run {
                view?.showAlert("Something went wrong")
            }
        }
    }

    override fun refineSearchResults(
        query: String,
        original: List<JourneyStation>
    ): List<JourneyStation> {
        val queryUpper = query.toUpperCase()
        return original.filter {
            it.fullName.toUpperCase().startsWith(queryUpper) or (it.crsCode.startsWith(queryUpper))
        }
    }

    override fun getMapDataForJourneyID(journeyId: String) {
        println("Started search for $journeyId ")
        launch {
            APIService.getJourneyData(journeyId)?.let {
                val journey = MapDataBuilder(stationMap).createMapDataFromAPIResponse(it)
                println(journey)
                view?.showMapData(journey)
            }

        }
//        view?.showMapData(getTestMapData())
    }

    private fun getStationList() {
        launch {
            APIService.getStationList().let { stationList ->
                val validStations = stationList?.stations?.filter { it.crs != null }
                stationMap.putAll(validStations!!.map { stationInfo ->
                    Pair(
                        stationInfo.crs!!,
                        stationInfo
                    )
                })
                val stationNames = validStations.map { JourneyStation(it) }
                stationNames.let { view?.populateStationList(it) }
            }
        }
    }

}
