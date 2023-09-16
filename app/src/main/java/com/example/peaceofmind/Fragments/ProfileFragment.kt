package com.example.peaceofmind.Fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.peaceofmind.LoginPage
import com.example.peaceofmind.MainActivity
import com.example.peaceofmind.R
import com.example.peaceofmind.RegisterPage
import com.google.firebase.auth.FirebaseAuth
import java.util.prefs.Preferences
import kotlin.math.log


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val logoutButton = view.findViewById<Button>(R.id.btn_logout)

        view.apply {
            logoutButton.setOnClickListener {
                auth.signOut()

                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", false)
                editor.apply()

                val intent = Intent(this@ProfileFragment.requireContext(), LoginPage::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

        return view
    }
}