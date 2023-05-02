package com.example.myageandzodiac

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.myageandzodiac.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_show_result.view.*
import java.time.LocalDate
import java.time.Period

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var dateOfBirth: LocalDate
    private lateinit var tillDate: LocalDate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateOfBirth = LocalDate.now()
        tillDate = LocalDate.now()

        binding.tvDateNow.text = "Current Date : $tillDate"

        dateAndZodiacPicker()

        binding.btSubmit.setOnClickListener {
            val age = getResult(dateOfBirth, tillDate)
            Log.d("MainActivity", "Age = $age")
            resultDialog(age)
        }
    }

    private fun dateAndZodiacPicker() {
        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            dateOfBirth = LocalDate.of(year, monthOfYear, dayOfMonth)
            binding.tvDob.text = "Date of Birth : $year-${monthOfYear + 1}-$dayOfMonth"

            val zodiac = when {
                (monthOfYear+1 == 1 && dayOfMonth >= 20 || monthOfYear+1 == 2 && dayOfMonth <= 19)
                -> "Aquarius"
                (monthOfYear+1 == 2 && dayOfMonth >= 20 || monthOfYear+1 == 3 && dayOfMonth <= 20)
                -> "Pisces"
                (monthOfYear+1 == 12 && dayOfMonth >= 22 || monthOfYear+1 == 1 && dayOfMonth <= 19)
                -> "Capricorn"
                (monthOfYear+1 == 11 && dayOfMonth >= 23 || monthOfYear+1 == 12 && dayOfMonth <= 21)
                -> "Sagittarius"
                (monthOfYear+1 == 10 && dayOfMonth >= 23 || monthOfYear+1 == 11 && dayOfMonth <= 22)
                -> "Scorpio"
                (monthOfYear+1 == 9 && dayOfMonth >= 23 || monthOfYear+1 == 10 && dayOfMonth <= 22)
                -> "Libra"
                (monthOfYear+1 == 8 && dayOfMonth >= 23 || monthOfYear+1 == 9 && dayOfMonth <= 22)
                -> "Virgo"
                (monthOfYear+1 == 7 && dayOfMonth >= 23 || monthOfYear+1 == 8 && dayOfMonth <= 22)
                -> "Leo"
                (monthOfYear+1 == 6 && dayOfMonth >= 21 || monthOfYear+1 == 7 && dayOfMonth <= 22)
                -> "Cancer"
                (monthOfYear+1 == 5 && dayOfMonth >= 21 || monthOfYear+1 == 6 && dayOfMonth <= 20)
                -> "Gemini"
                (monthOfYear+1 == 4 && dayOfMonth >= 21 || monthOfYear+1 == 5 && dayOfMonth <= 20)
                -> "Taurus"
                (monthOfYear+1 == 3 && dayOfMonth >= 21 || monthOfYear+1 == 4 && dayOfMonth <= 20)
                -> "Aries"
                else -> "Invalid input"
            }
            binding.tvZodiac.text = zodiac
        }
    }

    private fun getResult(startDate: LocalDate, endDate: LocalDate): String {

        val p = Period.between(startDate, endDate)

        return "Hello ${binding.edtName.text} \n" +
                "You are " + p.years + " years, " + p.months +
                " months and " + p.days +
                " days old. \n" +
                "And your zodiac is ${binding.tvZodiac.text}"
    }

    private fun resultDialog(msg: String) {
        val inflater = layoutInflater
        val alertLayout = inflater.inflate(R.layout.dialog_show_result, null)
        val dialog = AlertDialog.Builder(this)
            .setView(alertLayout)
            .setCancelable(true)
            .show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertLayout.dialog_message.text = msg

    }

}