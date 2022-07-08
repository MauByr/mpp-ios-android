package com.jetbrains.handson.mpp.mobile

import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyStation
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.MapData
import kotlinx.coroutines.CoroutineScope

interface ApplicationContract {
    interface View {
        fun setLabel(text: String)
        fun showAlert(msg: String)
        fun showResults(result: List<JourneyTableDataElem>)
        fun populateStationList(stations: List<JourneyStation>)
    }

    abstract class Presenter : CoroutineScope {
        abstract fun onViewTaken(view: View)
        abstract fun onSearchClicked(
            initialStation: String = "KGX",
            ultimateStation: String = "EDB",
            timeUTCString: String?
        )

        abstract fun refineSearchResults(
            query: String,
            original: List<JourneyStation>
        ): List<JourneyStation>

        abstract fun getMapDataForJourneyID(journeyId:String):MapData
    }

}
