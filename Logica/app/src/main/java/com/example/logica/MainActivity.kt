package com.example.logica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputs = arrayOf(input_1, input_2, input_3, input_4);

        for (input in inputs) {
            input.setOnFocusChangeListener() { view: View, b: Boolean ->
                checkAnswers()
            }
        }

    }

    private fun checkAnswers() {
        val correctAnswers = arrayOf("T", "F", "F", "T");
        val givenAnswers = arrayOf(input_1.text, input_2.text, input_3.text, input_4.text);
c
        val counter = 0;

        for (i in 0..3) {
            if (correctAnswers[i] == givenAnswers[i].toString()) {
                counter + 1;
            }
        }
        Toast.makeText(this, getString(R.string.correct) + counter, Toast.LENGTH_LONG).show()
    }
}
