package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rsrafprojekat2stefan_budimac_rn0618.R
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.ActivitySaveRecipeBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.MealContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.RecipeContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.MealViewModel
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class SaveRecipeActivity : AppCompatActivity() {
    private val mealViewModel: MealContract.ViewModel by viewModel<MealViewModel>()
    private lateinit var binding: ActivitySaveRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.e("IZVRSIO SE BINDING")
        init()
    }

    private fun init() {
        initUi()
        initListeners()
    }

    private fun initUi() {
        binding.newMealTitle.text = intent.getStringExtra("title").toString()
        Glide.with(this).load(intent.getStringExtra("imageUrl").toString()).dontAnimate().into(binding.saveMealImg)
        val spinnerOptions = arrayOf("Breakfast", "Lunch", "Dinner")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, spinnerOptions
        )
        binding.spinner.adapter = adapter
    }

    private fun initListeners() {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        binding.mealDate.hint = currentDate

        val myCalendar = Calendar.getInstance()

        val edittext = binding.mealDate as EditText
        val date =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabel(edittext, myCalendar)
            }

        edittext.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                DatePickerDialog(
                    this@SaveRecipeActivity, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]
                ).show()
            }
        })


        val formatter: DateFormat = SimpleDateFormat("MM/dd/yy")
        binding.addToMenu.setOnClickListener {
            val meal = Meal(
                intent.getStringExtra("title").toString(),
                intent.getStringExtra("imageUrl").toString(),
                intent.getStringExtra("id").toString(),
                formatter.parse(binding.mealDate.text.toString()),
                binding.spinner.selectedItem as String
            )
            mealViewModel.insertMeal(meal)
            Toast.makeText(this, "Meal added to your menu.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateLabel(editText: EditText, myCalendar: Calendar) {
        val myFormat = "MM/dd/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        editText.setText(sdf.format(myCalendar.getTime()))
    }
}