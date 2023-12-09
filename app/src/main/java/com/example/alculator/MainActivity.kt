

package com.example.alculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.alculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.pow
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Инициализация View Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Инициализация View Binding
        setContentView(binding.root) // Установка корневого представления

        // Теперь можно получить доступ к элементам макета через binding
        binding.btn0.setOnClickListener{ SetTextFields("0")}
        binding.btn1.setOnClickListener{ SetTextFields("1")}
        binding.btn2.setOnClickListener{ SetTextFields("2")}
        binding.btn3.setOnClickListener{ SetTextFields("3")}
        binding.btn4.setOnClickListener{ SetTextFields("4")}
        binding.btn5.setOnClickListener{ SetTextFields("5")}
        binding.btn6.setOnClickListener{ SetTextFields("6")}
        binding.btn7.setOnClickListener{ SetTextFields("7")}
        binding.btn8.setOnClickListener{ SetTextFields("8")}
        binding.btn9.setOnClickListener{ SetTextFields("9")}

        binding.btnComma.setOnClickListener{ SetTextFields(".")}
        binding.btnMinus.setOnClickListener{ SetTextFields("-")}
        binding.btnPlus.setOnClickListener{ SetTextFields("+")}
        binding.btnMultiplications.setOnClickListener{ SetTextFields("*")}
        binding.btnDivision.setOnClickListener{ SetTextFields("/")}
        binding.btnBracket1.setOnClickListener{ SetTextFields("(")}
        binding.btnBracket2.setOnClickListener{ SetTextFields(")")}

        binding.btnAC.setOnClickListener{
            binding.mathOperation.text=""
            binding.resultOperation.text=""
        }
        binding.btnDel.setOnClickListener {
            val str = binding.mathOperation.text.toString()
            if(str.isNotEmpty()){
                binding.mathOperation.text= str.substring(0, str.length-1)

                binding.resultOperation.text= ""
            }
        }
        fun round(value: Double, places: Int): Double {
            if (places < 0) throw IllegalArgumentException()

            val factor = 10.0.pow(places.toDouble())
            return (value * factor).roundToInt() / factor
        }
        binding.btnEqually.setOnClickListener {
            try {
                val expression = binding.mathOperation.text.toString()
                val ex = ExpressionBuilder(expression).build()
                val result = ex.evaluate()


                val roundedResult = round(result, 5)
                val longR = result.toLong()

                if (roundedResult == longR.toDouble()) {
                    binding.resultOperation.text = longR.toString()
                } else {
                    binding.resultOperation.text = roundedResult.toString()
                }


            } catch (e: ArithmeticException) {
                binding.resultOperation.text = "Error"
            } catch (e: Exception) {
                Log.d("Ошибка", "сообщение: ${e.message}")
            }
        }

    }
    fun SetTextFields(str: String) {
        binding.mathOperation.append(str) // Обращение к элементу с ID operation

    }}