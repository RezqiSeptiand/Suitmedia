package com.example.suitmediatest.ui.firstpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.suitmediatest.ui.secondpage.SecondScreenActivity
import com.example.suitmediatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.buttonNext.setOnClickListener {
            val nameTextValue = binding.nameEditText.text.toString()
            val palidrome = binding.palindromeEditText.text.toString()

            if (nameTextValue.isEmpty() || palidrome.isEmpty()) {
                Toast.makeText(this, "Fill the data", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondScreenActivity::class.java)
                intent.putExtra("NAME_TEXT", nameTextValue)
                startActivity(intent)
            }
        }

        binding.buttonCheck.setOnClickListener() {
            val palindrome = binding.palindromeEditText.text.toString()

            if (palindrome.isEmpty()) {
                Toast.makeText(this, "Fill the data", Toast.LENGTH_SHORT).show()
            } else {
                if (isPalindrome(palindrome)) {
                    Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun isPalindrome(input: String): Boolean {
        val cleanedInput = input.replace("\\s".toRegex(), "").lowercase()
        return cleanedInput == cleanedInput.reversed()
    }
}