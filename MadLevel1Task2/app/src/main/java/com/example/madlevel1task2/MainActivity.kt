package com.example.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener(){
            checkAll()
        }
    }

    private fun checkAll(){
        var counter: Int = 0

        var correct_answers = arrayOf(
            "T",
            "F",
            "F",
            "F"
        )

        var given_answers = arrayOf(
            binding.answer1.text.toString(),
            binding.answer2.text.toString(),
            binding.answer3.text.toString(),
            binding.answer4.text.toString()
        )

        for (i in 0 until 4){
            if(correct_answers[i] == given_answers[i]){
                counter++;
            }
        }

        if(counter > 0){
            if(counter == 4){
                Toast.makeText(this, getString(R.string.all_correct), Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, getString(R.string.correct, counter), Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
        }

    }
}