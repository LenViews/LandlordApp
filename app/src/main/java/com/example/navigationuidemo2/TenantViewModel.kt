package com.example.navigationuidemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TenantViewModel : ViewModel() {

    val fullName = MutableLiveData<String>()
    val unitNumber = MutableLiveData<String>()
    val monthlyRent = MutableLiveData<String>()

    // Holds the concatenated tenant info string to show
    private val _tenantInfo = MutableLiveData<String>("")
    val tenantInfo: LiveData<String> get() = _tenantInfo

    fun onAddTenantClicked() {
        val name = fullName.value.orEmpty()
        val unit = unitNumber.value.orEmpty()
        val rent = monthlyRent.value.orEmpty()

        if (name.isBlank() || unit.isBlank() || rent.isBlank()) {
            return
        }

        val newInfo = "Name: $name\nUnit: $unit\nRent: $rent\n\n"
        _tenantInfo.value = _tenantInfo.value?.plus(newInfo)

        // Optionally clear fields after add
        fullName.value = ""
        unitNumber.value = ""
        monthlyRent.value = ""
    }
}