package com.example.homework4_2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

var text1 = ""

class MainActivity : AppCompatActivity() {
    // 4 поля ввода
    var etName: EditText? = null
    var etSurname: EditText? = null
    var etPhone: EditText? = null
    var etAge: EditText? = null

    var bBTN1: Button? = null
    var bBTN2: Button? = null
    var bBTN3: Button? = null
    var bBTN4: Button? = null

    var orient = false

    // implement-ирую TextWatcher
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            val nameFilled = etName!!.text.toString()
            val surnameFilled = etSurname!!.text.toString()
            val phoneFilled = etPhone!!.text.toString()
            val ageFilled = etAge!!.text.toString()

            bBTN1?.setEnabled(!nameFilled.isEmpty() && !surnameFilled.isEmpty() && !phoneFilled.isEmpty() && !ageFilled.isEmpty())
        }

        override fun afterTextChanged(s: Editable) {}
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(applicationContext, MainActivity::class.java)

        etName = findViewById(R.id.editTextTextPersonName)
        etSurname = findViewById(R.id.editTextTextPersonSurname)
        etPhone = findViewById(R.id.editTextPhone)
        etAge = findViewById(R.id.editTextAge)
        bBTN1 = findViewById(R.id.button)
        bBTN2 = findViewById(R.id.button2)
        bBTN3 = findViewById(R.id.button3)
        bBTN4 = findViewById(R.id.button4)
        val textView: TextView = findViewById(R.id.textView)

        // поставил TextChangeListener для всех полей

        this.etName?.addTextChangedListener(textWatcher)
        this.etSurname?.addTextChangedListener(textWatcher)
        this.etPhone?.addTextChangedListener(textWatcher)
        this.etAge?.addTextChangedListener(textWatcher)

        bBTN1?.setOnClickListener {

            textView.text = "Name: ${etName?.text}| Surname: ${etSurname?.text}| Phone: ${etPhone?.text}| Age: ${etAge?.text}"
            it.hideKeyboard()
        }

        bBTN2?.setOnClickListener {
            if (!orient) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            orient = true
        }

        bBTN3?.setOnClickListener {
            onPause()
        }

        bBTN4?.setOnClickListener {
            onStop()
        }

    }

    override fun onPause() {
        super.onPause()
        Log.d("testMsg", "onPause")
        Toast.makeText(this@MainActivity, "You are into onPause function", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("testMsg", "onStop")
        Toast.makeText(this@MainActivity, "You are into onStop function", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("testMsg", "onResume")
        Toast.makeText(this@MainActivity, "You are into onResume function", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("testMsg", "onStart")
        Toast.makeText(this@MainActivity, "You are into onStart function", Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("testMsg", "onRestart")
        Toast.makeText(this@MainActivity, "You are into onRestart function", Toast.LENGTH_LONG).show()
    }

}

/*Задание 2
    Взять активити из ДЗ №3
    Реализовать разметку для горизонтального положения телефона
    Предусмотрите возможность сохранения состояния (при вызове onPause, onStop,
    закрытии приложения  и изменении ориентации экрана).
*/
//android:configChanges="keyboardHidden|orientation|screenSize" прописать в манифесте чтоб не перерисовывался экран