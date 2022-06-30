package com.jetbrains.handson.mpp.mobile

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.mobile.dataObjects.frontendDataObjects.JourneyTableDataElem

class MainActivity : AppCompatActivity(), ApplicationContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = ApplicationPresenter()
        presenter.onViewTaken(this)
        setupSearchListener(presenter)
        setupTable()
        populateStationList(listOf("1", "2"))
    }

    private fun setupTable() {
        findViewById<RecyclerView>(R.id.search_results)
    }

    override fun setLabel(text: String) {
        findViewById<TextView>(R.id.main_text).text = text
    }

    override fun showAlert(msg: String) {
        TODO("Not yet implemented")
    }

    override fun showResults(result: List<JourneyTableDataElem>) {
        findViewById<RecyclerView>(R.id.search_results).adapter = SearchResultAdapter(result)//.text = result.toString()
    }

    override fun populateStationList(stations: List<String>) {
        findViewById<Spinner>(R.id.from_station)?.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stations)
        findViewById<Spinner>(R.id.to_station)?.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stations)
    }

    private fun setupSearchListener(presenter: ApplicationPresenter) {
        val button: Button = findViewById(R.id.search_button)
        button.setOnClickListener { presenter.onSearchClicked(timeUTCString = null) }
    }
}
