package com.example.tipcal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcal.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.calcButton.setOnClickListener { calculateTip() }

     
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculateTip() {
     
        val costInput = binding.tipsForService.text.toString()
        val cost: Double = costInput.toDoubleOrNull() ?: run {
            binding.tipResult.text = "Invalid cost"
            return
        }

     
        val chosenId: Int = binding.tipOption.checkedRadioButtonId
        val tipPercentage: Double = when (chosenId) {
            R.id.option_ten_percent -> 0.1
            R.id.option_fifteen_percent -> 0.15
            R.id.option_twenty_percent -> 0.2
            else -> 0.05
        }

   
        var tip: Double = cost * tipPercentage
        val roundUp: Boolean = binding.roundTip.isChecked

        if (roundUp) {
            tip = ceil(tip)
        }

        val currency :String = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = String.format("Tip Amount: $%.2f", tip)
    }
}
