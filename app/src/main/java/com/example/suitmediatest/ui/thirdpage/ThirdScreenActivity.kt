package com.example.suitmediatest.ui.thirdpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.adapter.UserListAdapter
import com.example.suitmediatest.ui.ViewModelFactory
import com.example.suitmediatest.databinding.ActivityThirdScreenBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        setupRecyclerView()
    }

    private fun setupAction() {
        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            setupRecyclerView()
            viewModel.isLoading.observe(this) {
                binding.swipeRefresh.isRefreshing = it == true
            }
        }
    }

    private fun setupRecyclerView() {
        val adapter = UserListAdapter { firstName, lastName ->
            val resultIntent = Intent().apply {
                putExtra("FIRST_NAME", firstName)
                putExtra("LAST_NAME", lastName)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch {
            viewModel.users?.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}

