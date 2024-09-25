package com.example.tipcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.math.ceil
import com.example.tipcalculator.R


class MainActivity : AppCompatActivity() {

    private lateinit var tipsForServiceEditText: EditText
    private lateinit var tipOptionRadioGroup: RadioGroup
    private lateinit var roundTipSwitch: Switch
    private lateinit var calcButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        tipsForServiceEditText = findViewById(R.id.tips_for_service)
        tipOptionRadioGroup = findViewById(R.id.tip_option)
        roundTipSwitch = findViewById(R.id.round_tip)
        calcButton = findViewById(R.id.calc_button)

        // Set the calculate button click listener
        calcButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val tipInput = tipsForServiceEditText.text.toString()

        // Check if the tip input is empty
        if (tipInput.isEmpty()) {
            Toast.makeText(this, "Please enter the tip amount", Toast.LENGTH_SHORT).show()
            return
        }

        // Parse the base amount entered by the user
        val baseAmount = tipInput.toDoubleOrNull()

        if (baseAmount == null || baseAmount <= 0) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            return
        }

        // Get the selected tip percentage based on the selected RadioButton
        val tipPercentage = when (tipOptionRadioGroup.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_fifteen_percent -> 0.15
            R.id.option_ten_percent -> 0.10
            else -> {
                Toast.makeText(this, "Please select a service rating", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Calculate the tip
        var tipAmount = baseAmount * tipPercentage

        // If round tip switch is enabled, round up the tip
        if (roundTipSwitch.isChecked) {
            tipAmount = ceil(tipAmount)
        }

        // Format the tip amount to 2 decimal places
        val formattedTip = DecimalFormat("#.##").format(ttipAmount)

        // Display the calculated tip in a Toast or update a UI element if needed
        Toast.makeText(this, "Tip Amount: $$formattedTip", Toast.LENGTH_LONG).show()
    }
}
