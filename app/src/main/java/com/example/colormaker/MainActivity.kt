package com.example.colormaker

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch


class MainActivity : AppCompatActivity() {

    private lateinit var resetButton: Button
    private lateinit var redSlider: SeekBar
    private lateinit var greenSlider: SeekBar
    private lateinit var blueSlider: SeekBar
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var redSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var greenSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var blueSwitch: Switch
    private lateinit var redText: EditText
    private lateinit var greenText: EditText
    private lateinit var blueText: EditText
    private lateinit var colorBox: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //match with the UI elements
        resetButton = findViewById(R.id.resetButton)
        redSlider = findViewById(R.id.redSeekBar)
        greenSlider = findViewById(R.id.greenSeekBar)
        blueSlider = findViewById(R.id.blueSeekBar)
        redSwitch = findViewById(R.id.redSwitch)
        greenSwitch = findViewById(R.id.greenSwitch)
        blueSwitch = findViewById(R.id.blueSwitch)
        redText = findViewById(R.id.redValue)
        greenText = findViewById(R.id.greenValue)
        blueText = findViewById(R.id.blueValue)
        colorBox = findViewById(R.id.colorBox)

        //set up initial state
        var redValue = 1.0
        var greenValue = 1.0
        var blueValue = 1.0
        redSlider.isEnabled = true
        blueSlider.isEnabled = true
        greenSlider.isEnabled = true
        redSwitch.isChecked = true
        blueSwitch.isChecked = true
        greenSwitch.isChecked = true
        redText.isFocusable = true
        redText.isFocusableInTouchMode = true
        greenText.isFocusable = true
        greenText.isFocusableInTouchMode = true
        blueText.isFocusable = true
        blueText.isFocusableInTouchMode = true
        redSlider.max = 255
        blueSlider.max = 255
        greenSlider.max = 255
        redSlider.progress = (redValue*255).toInt()
        greenSlider.progress = (greenValue*255).toInt()
        blueSlider.progress = (blueValue*255).toInt()
        updateColorBox(redValue, greenValue, blueValue) // make initial color

        //Temp values for switches to remember where they were
        var redTemp = 255
        var greenTemp = 255
        var blueTemp = 255


        //Set up Red levels
        //Disable Red Changes if Red Switch is Disabled
        redSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                redSlider.isEnabled = true
                redText.isFocusable = true
                redText.isFocusableInTouchMode = true
                redSlider.progress = redTemp
                redValue = redSlider.progress/255.0
                redText.text = Editable.Factory.getInstance().newEditable(redValue.toString()) // convert to editable
                updateColorBox(redValue, greenValue, blueValue) // function to change color
            } else {
                redTemp = redSlider.progress
                redSlider.isEnabled = false
                redText.isFocusable = false
                redText.isFocusableInTouchMode = false
                redValue = 0.0
                updateColorBox(redValue, greenValue, blueValue)
            }
        }

        //Make red slider change the color and text view
        redSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Update the red value as the seek bar's progress changes
                redValue = progress/255.0
                redText.text = Editable.Factory.getInstance().newEditable(redValue.toString()) // Display the red value
                updateColorBox(redValue, greenValue, blueValue)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something when user starts touching the seek bar, don't need it
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something when user stops touching the seek bar, don't need it
            }
        })

        //Make you able to change color and slider through the the text view
        redText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val inputText = redText.text.toString().toDoubleOrNull()
                if (inputText != null){
                    if (inputText in 0.0..1.0) {
                    redValue = inputText
                    redSlider.progress = (redValue * 255).toInt() // Match the red slider to the text value
                    updateColorBox(redValue, greenValue, blueValue)
                }
                }
            }
        }
        //Set up Green Levels, basically same as red
        greenSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                greenSlider.isEnabled = true
                greenText.isFocusable = true
                greenText.isFocusableInTouchMode = true
                greenSlider.progress = greenTemp
                greenValue = greenSlider.progress/255.0
                greenText.text = Editable.Factory.getInstance().newEditable(greenValue.toString())
                updateColorBox(redValue, greenValue, blueValue)
            } else {
                greenTemp = greenSlider.progress
                greenSlider.isEnabled = false
                greenText.isFocusable = false
                greenText.isFocusableInTouchMode = false
                greenValue = 0.0
                updateColorBox(redValue, greenValue, blueValue)
            }
        }

        greenSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Update the red value as the seek bar's progress changes
                greenValue = progress/255.0
                greenText.text = Editable.Factory.getInstance().newEditable(greenValue.toString()) // Display the green value
                updateColorBox(redValue, greenValue, blueValue)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        greenText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val inputText = greenText.text.toString().toDoubleOrNull()
                if (inputText != null){
                    if (inputText in 0.0..1.0) {
                        greenValue = inputText
                        greenSlider.progress = (greenValue * 255).toInt() // Match the red slider to the text value
                        updateColorBox(redValue, greenValue, blueValue)
                    }
                }
            }
        }

        //Set up Blue levels
        blueSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                blueSlider.isEnabled = true
                blueText.isFocusable = true
                blueText.isFocusableInTouchMode = true
                blueSlider.progress = blueTemp
                blueValue = blueSlider.progress/255.0
                blueText.text = Editable.Factory.getInstance().newEditable(blueValue.toString())
                updateColorBox(redValue, greenValue, blueValue)
            } else {
                blueTemp = blueSlider.progress
                blueSlider.isEnabled = false
                blueText.isFocusable = false
                blueText.isFocusableInTouchMode = false
                blueValue = 0.0
                updateColorBox(redValue, greenValue, blueValue)
            }
        }

        blueSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Update the red value as the seek bar's progress changes
                blueValue = progress/255.0
                blueText.text = Editable.Factory.getInstance().newEditable(blueValue.toString())
                updateColorBox(redValue, greenValue, blueValue)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        blueText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val inputText = blueText.text.toString().toDoubleOrNull()
                if (inputText != null){
                    if (inputText in 0.0..1.0) {
                        blueValue = inputText
                        blueSlider.progress = (blueValue * 255).toInt() // Match the red slider to the text value
                        updateColorBox(redValue, greenValue, blueValue)
                    }
                }
            }
        }

        //resets back to default
        resetButton.setOnClickListener {
            redSlider.isEnabled = true
            blueSlider.isEnabled = true
            greenSlider.isEnabled = true
            redSwitch.isChecked = true
            blueSwitch.isChecked = true
            greenSwitch.isChecked = true
            redText.isFocusable = true
            redText.isFocusableInTouchMode = true
            greenText.isFocusable = true
            greenText.isFocusableInTouchMode = true
            blueText.isFocusable = true
            blueText.isFocusableInTouchMode = true
            redValue = 1.0
            greenValue = 1.0
            blueValue = 1.0
            redSlider.progress = (redValue*255).toInt()
            greenSlider.progress = (greenValue*255).toInt()
            blueSlider.progress = (blueValue*255).toInt()
            updateColorBox(redValue, greenValue, blueValue)
        }



    }
    //what actually dynamically should changes the color
    private fun updateColorBox(redValue: Double, greenValue: Double, blueValue: Double) {
        colorBox.setBackgroundColor(
            Color.rgb((redValue * 255).toInt(), (greenValue * 255).toInt(), (blueValue * 255).toInt())
        )
    }
}