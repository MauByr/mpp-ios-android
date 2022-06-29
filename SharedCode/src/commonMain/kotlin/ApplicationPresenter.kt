package com.jetbrains.handson.mpp.mobile

import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext



class ApplicationPresenter : ApplicationContract.Presenter() {

    private val dispatchers = AppDispatchersImpl()
    private var view: ApplicationContract.View? = null
    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job

    override fun onViewTaken(view: ApplicationContract.View) {
        this.view = view
        view.setLabel(createApplicationScreenMessage() + "!!!!")
        getStationList()
    }

    override fun onSearchClicked() {
        launch {
            APIService.getJourneyList(JourneyQuery("KGX", "EDB"))?.let { journey ->
                println(journey)
                view?.showResults(journey.outboundJourneys.map { elem -> JourneyTableDataElem(elem) })
            }
        }
    }

    private fun getStationList() {
        launch {
            APIService.getStationList().let { stationList ->
                val stationNames = stationList?.stations?.mapNotNull { it.name }
                stationNames?.let { view?.populateStationList(it) }
            }
        }
    }

}
