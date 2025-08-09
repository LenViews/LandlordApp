package com.example.navigationuidemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class TenantViewModel : ViewModel() {
    // Store raw tenant data
    private val _tenantInfo = MutableLiveData<String>("")
    val tenantInfo: LiveData<String> = _tenantInfo

    // Transform to uppercase for display
    val capitalizedTenantInfo: LiveData<String> = tenantInfo.map { it.uppercase() }

    // Tenant counter
    private val _tenantCount = MutableLiveData<Int>(0)
    val tenantCount: LiveData<Int> = _tenantCount

    fun addTenant(name: String, unit: String, rent: String) {
        val newEntry = "Name: $name\nUnit: $unit\nRent: $rent\n\n"
        _tenantInfo.value = (_tenantInfo.value ?: "") + newEntry
        _tenantCount.value = (_tenantCount.value ?: 0) + 1
    }

    // Clear all tenant data
    fun clearAll() {
        _tenantInfo.value = ""
        _tenantCount.value = 0
    }
}