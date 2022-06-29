package com.jetbrains.handson.mpp.mobile

import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


const val rootURL = "https://mobile-api-softwire2.lner.co.uk/v1/"

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
            val res = APIService.getJourneyList(JourneyQuery("KGX", "EDB"))
            println(res)
            if (res != null) {
                view?.showResults(res.outboundJourneys.map { JourneyTableDataElem(it) })
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
