package com.example.persistence

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import android.annotation.SuppressLint as SuppressLint1

const val LOG_TAG = "MainActivity"
const val COUNTER_KEY = "counter"

abstract class MainActivity : AppCompatActivity() {

     abstract val viewModel: ViewModel
    private lateinit var colorView: View
    private lateinit var resetButton: Button
    private lateinit var redTextView: TextView
    private lateinit var greenTextView: TextView
    private lateinit var blueTextView: TextView
    private lateinit var redSwitch: SwitchCompat
    private lateinit var greenSwitch: SwitchCompat
    private lateinit var blueSwitch: SwitchCompat
    private lateinit var redSeekBar: SeekBar
    private lateinit var greenSeekBar: SeekBar
    private lateinit var blueSeekBar: SeekBar
    private var colorRed = 0
    private var colorGreen = 0
    private var colorBlue = 0


    @android.annotation.SuppressLint("CutPasteId")
    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorView= findViewById(R.id.colorView)
        colorView.setOnClickListener{
            viewModel.increaseCount()
        }

        redTextView = findViewById(R.id.number1)
        redTextView.text = viewModel.getCount().toString()

        greenTextView = findViewById(R.id.number2)
        greenTextView.text = viewModel.getCount().toString()

        blueTextView = findViewById(R.id.number3)
        blueTextView.text = viewModel.getCount().toString()

        redSwitch = findViewById(R.id.firstSwitch)
        redSwitch.text = viewModel.getCount().toString()

        greenSwitch = findViewById(R.id.firstSwitch)
        greenSwitch.text = viewModel.getCount().toString()

        blueSwitch = findViewById(R.id.firstSwitch)
        blueSwitch.text = viewModel.getCount().toString()

        redSeekBar = findViewById(R.id.seekBar)
        redSeekBar.setOnClickListener {
            viewModel.increaseCount()
        }
        greenSeekBar = findViewById(R.id.seekBar2)
        greenSeekBar.setOnClickListener {
            viewModel.increaseCount()
        }
        blueSeekBar = findViewById(R.id.seekBar3)
        blueSeekBar.setOnClickListener {
            viewModel.increaseCount()
        }

        resetButton = findViewById(R.id.button)
        resetButton.setOnClickListener{
            viewModel.increaseCount()
            redTextView.text = viewModel.getCount().toString()
            greenTextView.text = viewModel.getCount().toString()
            blueTextView.text = viewModel.getCount().toString()
        }
        fun onDestroy() {
            super.onDestroy()
            Log.d(LOG_TAG, "onDestroy called")
        }
        fun onStart() {
            super.onStart()
            Log.d(LOG_TAG, "onStart called")
        }
        fun onStop() {
            super.onStop()
            Log.d(LOG_TAG, "onStop called")
        }
        fun onResume() {
            super.onResume()
            Log.d(LOG_TAG, "onResume called")
        }
        fun onPause() {
            super.onPause()
            Log.d(LOG_TAG, "onPause called")
        }
        fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            Log.d(LOG_TAG, "The counter value is saved")

            outState.putInt(COUNTER_KEY, viewModel.getCount())
        }

        val viewModel: ViewModel by lazy {
            PreferencesRepository.initialize(this)
            ViewModelProvider(this)[ViewModel::class.java]
        }




        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.teal_700)))


        val button: Button? = findViewById(R.id.button)




        val colorView: View = findViewById(R.id.colorView)
        val firstSwitch: SwitchCompat = findViewById(R.id.firstSwitch)
        val switch2: SwitchCompat = findViewById(R.id.switch2)
        val switch3: SwitchCompat = findViewById(R.id.switch3)


        val seek = findViewById<SeekBar>(R.id.seekBar)
        seek.isEnabled = false
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint1("ResourceAsColor")
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                colorRed = progress
                redTextView.text = (progress * 0.00392157).toString()
                colorView.setBackgroundColor(Color.rgb(colorRed, colorGreen, colorBlue))
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {

                Toast.makeText(
                    this@MainActivity,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val seek2 = findViewById<SeekBar>(R.id.seekBar2)
        seek2.isEnabled = false
        seek2?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                colorGreen = progress
                greenTextView.text = (progress * 0.00392157).toString()
                colorView.setBackgroundColor(Color.rgb(colorRed, colorGreen, colorBlue))
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {

                Toast.makeText(
                    this@MainActivity,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val seek3 = findViewById<SeekBar>(R.id.seekBar3)
        seek3.isEnabled = false
        seek3?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                colorBlue = progress
                blueTextView.text = (progress * 0.00392157).toString()
                colorView.setBackgroundColor(Color.rgb(colorRed, colorGreen, colorBlue))
            }

            override fun onStartTrackingTouch(seek: SeekBar) {


            }

            override fun onStopTrackingTouch(seek: SeekBar) {

                Toast.makeText(
                    this@MainActivity,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        button?.setOnClickListener {
            colorView.setBackgroundColor(Color.rgb(0, 0, 0))
            seek.progress = 0
            seek2.progress = 0
            seek3.progress = 0


        }
        firstSwitch.setOnCheckedChangeListener { _, onSwitch ->
            seek.isEnabled = onSwitch
            seek.max = 255
        }
        switch2.setOnCheckedChangeListener { _, onSwitch ->
            seek2.isEnabled = onSwitch
            seek2.max = 255
        }
        switch3.setOnCheckedChangeListener { _, onSwitch ->
            seek3.isEnabled = onSwitch
            seek3.max = 255
        }
        button?.setOnClickListener{
            firstSwitch.isChecked = false
            switch2.isChecked = false
            switch3.isChecked = false
        }




    }

}








