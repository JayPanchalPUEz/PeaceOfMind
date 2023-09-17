package com.example.peaceofmind

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class RegisterPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val registerButton = findViewById<Button>(R.id.btn_register)
        var nameRegistered = findViewById<EditText>(R.id.name_register).text.toString()


        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_register).text.toString()
            val password = findViewById<EditText>(R.id.password_register).text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                        val username = nameRegistered
                        val editor = sharedPreferences.edit()
                        editor.putString("user_name", nameRegistered)
                        editor.apply()
                        val intent = Intent(this, LoginPage::class.java)
//                        intent.putExtra("UserName", username)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Registration Failed! Please Try Again", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        val LoginLink =findViewById<TextView>(R.id.have_acc_register)

        LoginLink.setOnClickListener{
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}