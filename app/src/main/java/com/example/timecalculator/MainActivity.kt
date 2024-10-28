package com.example.timecalculator

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar

    private lateinit var textResult: TextView
    private lateinit var buttonSum: Button
    private lateinit var buttonDif: Button

    private lateinit var h1: EditText
    private lateinit var m1: EditText
    private lateinit var s1: EditText
    private lateinit var h2: EditText
    private lateinit var m2: EditText
    private lateinit var s2: EditText



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when(item.itemId) {
                R.id.reset -> {
                    h1.text.clear();m1.text.clear();s1.text.clear()
                    h2.text.clear();m2.text.clear();s2.text.clear()
                    textResult.text = "Результат"
                    message("Данные очищены").show()
                }

                R.id.exit -> {
                    message("Приложение закрыто").show();finish()
                }
            }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "  Калькулятор времени"
        toolbarMain.subtitle = "                    версия 1.0"
        toolbarMain.setLogo(R.drawable.logo)

        h1 = findViewById(R.id.h1)
        m1 = findViewById(R.id.m1)
        s1 = findViewById(R.id.s1)
        h2 = findViewById(R.id.h2)
        m2 = findViewById(R.id.m2)
        s2 = findViewById(R.id.s2)



        buttonDif = findViewById(R.id.buttonDif)
        buttonSum = findViewById(R.id.buttonSum)
        textResult = findViewById(R.id.textResult)


    }

    fun message(message: String) = Toast.makeText(applicationContext,message,Toast.LENGTH_LONG)

    fun operation(v: View) {
        var h1r: Int; var m1r: Int; var s1r: Int
        var h2r: Int; var m2r: Int; var s2r: Int

        try {  h1r = h1.text.toString().toInt() * 3600 } catch (_:Exception) { h1r = 0 }
        try {  m1r = m1.text.toString().toInt() * 60 } catch (_:Exception) { m1r = 0 }
        try {  s1r = s1.text.toString().toInt() } catch (_:Exception) { s1r = 0 }
        try {  h2r = h2.text.toString().toInt() * 3600 } catch (_:Exception) { h2r = 0 }
        try {  m2r = m2.text.toString().toInt() * 60 } catch (_:Exception) { m2r = 0 }
        try {  s2r = s2.text.toString().toInt() } catch (_:Exception) { s2r = 0 }

        val result1 = h1r + m1r + s1r
        val result2 = h2r + m2r + s2r

          val result = when(v.id){
           R.id.buttonSum -> result1 + result2
           R.id.buttonDif -> result1 - result2
           else -> 0 }
        val hr: Int = result / 3600
        val mr: Int = (result - hr*3600)/60
        val sr: Int = result - (hr*3600 + mr*60)
         textResult.setTextColor(resources.getColor(R.color.color2))
         textResult.text = "${hr}h  ${mr}m  ${sr}s"
        message(textResult.text.toString()).show()
    }

}