package com.jetbrains.handson.mpp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity(), ApplicationContract.View {
    var fromSpinner : Spinner? = null
    var toSpinner : Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = ApplicationPresenter()
        presenter.onViewTaken(this)
        fromSpinner = findViewById(R.id.from_station)
        toSpinner = findViewById(R.id.to_station)
        setupSearchListener(presenter)

    }

    override fun setLabel(text: String) {
        findViewById<TextView>(R.id.main_text).text = text
    }

    override fun showAlert(msg: String) {
        TODO("Not yet implemented")
    }

    override fun showResults(result: String) {
        findViewById<TextView>(R.id.search_results).text = result
    }

    override fun populateStationList(stations :List<String>){

    }
    fun setupSearchListener(presenter: ApplicationPresenter) {
        val button: Button = findViewById(R.id.search_button)
        button.setOnClickListener { presenter.onSearchClicked() }
    }
}
