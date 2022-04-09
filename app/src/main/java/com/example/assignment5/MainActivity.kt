package com.example.assignment5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.assignment5.data.Stat
import com.example.assignment5.data.StatDao

class MainActivity : AppCompatActivity() {

    private lateinit var saveBtn: Button

    private lateinit var editTextDistR: EditText
    private lateinit var editTextDistS: EditText
    private lateinit var editTextC: EditText


    private lateinit var avgDistRanTextView: TextView
    private lateinit var avgDistSwamTextView: TextView
    private lateinit var avgCaloriesTextView: TextView
    private lateinit var totalDistTextView: TextView

    private var db: StatDao = App.instance.db.statsDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findWidgets()
        populateTextViews()


        saveBtn.setOnClickListener {
            val distRan = editTextDistR.text.toString().toDouble()
            val distSwam = editTextDistS.text.toString().toDouble()
            val calsConsumed = editTextC.text.toString().toDouble()

            db.insertAll(Stat(0, distRan, distSwam, calsConsumed))

            editTextDistR.text.clear()
            editTextDistS.text.clear()
            editTextC.text.clear()

            populateTextViews()
        }

    }


    private fun populateTextViews() {
        val dbResult = getData()

        avgDistRanTextView.text = dbResult.avgRan.toString()
        avgDistSwamTextView.text = dbResult.avgSwam.toString()
        avgCaloriesTextView.text = dbResult.avgCaloriesConsumed.toString()
        totalDistTextView.text =dbResult.totalRan.toString()

    }

    private fun getData() : DbResult{
        val stats: List<Stat> = db.getAll()

        val ran = stats.map { x -> x.distanceRan }
        val avgR = ran.average()
        val totalR = ran.sum()

        val avgS = stats.map { x -> x.distanceSwam }.average()

        val avgCal = stats.map { x -> x.caloriesConsumed }.average()

        return DbResult(avgR,avgS,avgCal,totalR)
    }

    private fun findWidgets() {
        saveBtn = findViewById(R.id.saveButton)

        editTextDistR = findViewById(R.id.editTextDistanceRan)
        editTextDistS = findViewById(R.id.editTextDistanceSwam)
        editTextC = findViewById(R.id.editTextCaloriesConsumed)

        avgDistRanTextView = findViewById(R.id.averageDistRan)
        avgDistSwamTextView = findViewById(R.id.averageDistSwam)
        avgCaloriesTextView = findViewById(R.id.averageCalories)
        totalDistTextView = findViewById(R.id.totalDistance)
    }
}