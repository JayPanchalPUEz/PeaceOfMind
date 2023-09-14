package com.example.peaceofmind

import android.content.Intent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        auth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.btn_register)

        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_register).text.toString()
            val password = findViewById<EditText>(R.id.password_register).text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginPage::class.java)
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