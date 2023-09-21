package com.example.peaceofmind.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peaceofmind.R
import com.google.android.material.bottomnavigation.BottomNavigationView

open class HomeFragment : Fragment() {

    private lateinit var username:TextView
    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val nameReg = sharedPreferences.getString("username", "")

        username = view.findViewById(R.id.username_home)


        username.text = nameReg

//        val meditateButton = view.findViewById<Button>(R.id.meditate_btn)
//        meditateButton.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_meditateFragment)
//
//            activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)?.selectedItemId = R.id.navigation_meditate
//
//        }

        return view
    }
}