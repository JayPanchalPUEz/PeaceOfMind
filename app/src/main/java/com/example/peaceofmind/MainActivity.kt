package com.example.peaceofmind

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.peaceofmind.Fragments.HomeFragment
import com.example.peaceofmind.Fragments.MeditateFragment
import com.example.peaceofmind.Fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val meditateFragment = MeditateFragment()
    private val profileFragment = ProfileFragment()

    private lateinit var activeFragment: Fragment
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var userName = sharedPreferences.getString("user_name", "")
        val intent = intent
        intent.putExtra("UserName", userName)


        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, meditateFragment, "meditate").hide(meditateFragment)
            add(R.id.fragment_container, profileFragment, "profile").hide(profileFragment)
            add(R.id.fragment_container, homeFragment, "home")
        }.commit()

        activeFragment = homeFragment

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> showFragment(homeFragment)
                R.id.navigation_meditate -> showFragment(meditateFragment)
                R.id.navigation_profile -> showFragment(profileFragment)
            }
            true
        }


    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }
}