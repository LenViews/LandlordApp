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
        
        // Data Binding setup
        binding = DataBindingUtil.setContentView(this, R.layout.addtenant_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Button click handlers
        binding.button.setOnClickListener {
            val name = binding.fullName.text.toString()
            val unit = binding.unitNumber.text.toString()
            val rent = binding.fullRent.text.toString()

            if (name.isNotEmpty() && unit.isNotEmpty() && rent.isNotEmpty()) {
                viewModel.addTenant(name, unit, rent)
                clearInputFields()
            }
        }

        binding.btnBack.setOnClickListener {
            returnToMainActivity()
        }
    }

    private fun clearInputFields() {
        binding.fullName.text?.clear()
        binding.unitNumber.text?.clear()
        binding.fullRent.text?.clear()
    }

    private fun returnToMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("TotalTenants", viewModel.tenantCount.value ?: 0)
        }
        startActivity(intent)
        finish()
    }
}