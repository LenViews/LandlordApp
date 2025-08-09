package com.example.navigationuidemo2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var tvTotalTenants: TextView
    private lateinit var tvTotalRent: TextView

    // Improved Activity Result API contract
    private val addTenantLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { data ->
                val tenantCount = data.getIntExtra("TotalTenants", 0)
                val totalRent = data.getIntExtra("TotalRent", 0)
                
                updateDashboard(tenantCount, totalRent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d("LIFECYCLE", "MainActivity - onCreate")
        initializeViews()
        setupNavigationDrawer()
        handleIntentExtras()
    }

    private fun initializeViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.topAppBar)
        tvTotalRent = findViewById(R.id.tvTotalRentExpected)
        tvTotalTenants = findViewById(R.id.tvTotalRegisteredTenants)
    }

    private fun setupNavigationDrawer() {
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_dashboard -> {
                    Toast.makeText(this, "Dashboard clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_add_tenant -> {
                    addTenantLauncher.launch(Intent(this, AddTenant::class.java))
                    true
                }
                R.id.nav_logout -> {
                    Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }.also {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

    private fun handleIntentExtras() {
        intent?.extras?.let {
            updateDashboard(
                it.getInt("TotalTenants", 0),
                it.getInt("TotalRent", 0)
            )
        }

        findViewById<Button>(R.id.btnAddTenants).setOnClickListener {
            addTenantLauncher.launch(Intent(this, AddTenant::class.java))
        }
    }

    private fun updateDashboard(tenantCount: Int, totalRent: Int) {
        tvTotalTenants.text = "Total Tenants: $tenantCount"
        tvTotalRent.text = "Total Rent Collected: $totalRent"
    }

    // Lifecycle logging kept as before
    override fun onStart() { super.onStart(); Log.d("LIFECYCLE", "onStart") }
    override fun onResume() { super.onResume(); Log.d("LIFECYCLE", "onResume") }
    override fun onPause() { super.onPause(); Log.d("LIFECYCLE", "onPause") }
    override fun onStop() { super.onStop(); Log.d("LIFECYCLE", "onStop") }
    override fun onRestart() { super.onRestart(); Log.d("LIFECYCLE", "onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.d("LIFECYCLE", "onDestroy") }
}