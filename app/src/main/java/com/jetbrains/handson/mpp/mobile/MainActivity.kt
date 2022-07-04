package com.jetbrains.handson.mpp.mobile

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyStation
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ApplicationContract.View {
    private var departureSpinner : Spinner? = null
    private var arrivalSpinner : Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = ApplicationPresenter()
        presenter.onViewTaken(this)
        setupSearchListener(presenter)
    }

    override fun setLabel(text: String) {
        main_text.text = text
    }

    override fun showAlert(msg: String) {
        TODO("Not yet implemented")
    }

    override fun showResults(result: List<JourneyTableDataElem>) {
        search_results.adapter = SearchResultAdapter(result)
        if (result.isEmpty()) {
            searchStatus.text = getString(R.string.message_no_trains)
        } else {
            searchStatus.visibility = View.GONE
        }
    }

    override fun populateStationList(stations: List<JourneyStation>) {
        departureSpinner = findViewById(R.id.from_station)
        arrivalSpinner = findViewById(R.id.to_station)
        departureSpinner?.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stations)
        arrivalSpinner?.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stations)
    }

    private fun setupSearchListener(presenter: ApplicationPresenter) {
        val button: Button = findViewById(R.id.search_button)
        button.setOnClickListener {
            searchStatus.text = getString(R.string.searching_text)
            searchStatus.visibility = View.VISIBLE
            val fromStation = departureSpinner?.selectedItem as JourneyStation?
            val toStation = arrivalSpinner?.selectedItem as JourneyStation?
            if (fromStation != null && toStation != null) {
                presenter.onSearchClicked(
                    fromStation.crsCode,
                    toStation.crsCode,
                    timeUTCString = null
                )
            }


        }
    }
}
