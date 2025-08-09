package com.example.navigationuidemo2

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.navigationuidemo2.databinding.AddtenantActivityBinding

class AddTenant : AppCompatActivity() {

    private lateinit var binding: AddtenantActivityBinding
    private val viewModel: TenantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.addtenant_activity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnBack.setOnClickListener {
            val totalTenants = 1 // can pass real data if available
            val totalRent = 5000
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("TotalTenants", totalTenants)
            intent.putExtra("TotalRent", totalRent)
            startActivity(intent)
        }
    }
}
