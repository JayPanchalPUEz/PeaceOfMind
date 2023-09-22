package com.example.peaceofmind

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.peaceofmind.Fragments.HomeFragment
import com.example.peaceofmind.Fragments.MeditateFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val meditateFragment = MeditateFragment()

    private lateinit var activeFragment: Fragment
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, meditateFragment, "meditate").hide(meditateFragment)
            add(R.id.fragment_container, homeFragment, "home")
        }.commit()

        activeFragment = homeFragment

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> showFragment(homeFragment)
                R.id.navigation_meditate -> showFragment(meditateFragment)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout_btn -> {
                auth.signOut()

                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", false)
                editor.apply()

                val intent = Intent(this, LoginPage::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}