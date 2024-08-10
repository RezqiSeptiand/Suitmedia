package com.example.suitmediatest.ui.firstpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                    AlertDialog.Builder(this).apply {
                        setTitle("isPalindrome")
                        setMessage("$palindrome are Palindrome")
                        setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        create().show()
                    }
                } else {
                    AlertDialog.Builder(this).apply {
                        setTitle("Not Palindrome")
                        setMessage("$palindrome are not palindrome")
                        setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        create().show()
                    }
                }
            }
        }
    }

    private fun isPalindrome(input: String): Boolean {
        val cleanedInput = input.replace("\\s".toRegex(), "").lowercase()
        return cleanedInput == cleanedInput.reversed()
    }
}