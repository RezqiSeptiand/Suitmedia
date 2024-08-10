package com.example.suitmediatest.ui.secondpage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suitmediatest.ui.thirdpage.ThirdScreenActivity
import com.example.suitmediatest.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        setupView()
    }

    private fun setupAction() {
        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.buttonChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivityForResult(intent, START_ACTIVITY_3_REQUEST_CODE)
        }
    }

    private fun setupView() {
        val nameText = intent.getStringExtra("NAME_TEXT")
        binding.nameTextView.text = nameText

        val firstName = intent.getStringExtra("FIRST_NAME")
        val lastName = intent.getStringExtra("LAST_NAME")

        val fullName = "${firstName ?: "Selected User Name"} ${lastName ?: ""}"
        binding.selectedTextView.text = fullName
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == START_ACTIVITY_3_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val firstName = data?.getStringExtra("FIRST_NAME")
                val lastName = data?.getStringExtra("LAST_NAME")

                val fullName = "$firstName $lastName"
                binding.selectedTextView.text = fullName
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        const val START_ACTIVITY_3_REQUEST_CODE = 0
    }
}
