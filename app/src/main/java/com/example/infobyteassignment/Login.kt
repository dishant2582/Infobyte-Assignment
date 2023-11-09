package com.example.infobyteassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signInBtn = findViewById<Button>(R.id.loginBtn)
        val uniqueId = findViewById<TextInputEditText>(R.id.etId)
        val password = findViewById<TextInputEditText>(R.id.etPassword)
        val tv = findViewById<TextView>(R.id.tv)

        signInBtn.setOnClickListener {

            val msg1 = uniqueId.text.toString()
            val msg2 = password.text.toString()

            if(msg1.isNotEmpty() && msg2.isNotEmpty())
            {
                readData(msg1, msg2)
            }
            else if(msg1.isEmpty())
            {
                Toast.makeText(this,"Please enter your Unique Id", Toast.LENGTH_SHORT).show()
            }

            else if(msg2.isEmpty())
            {
                Toast.makeText(this,"Please enter your Password", Toast.LENGTH_SHORT).show()
            }

            else
            {
                Toast.makeText(this,"Please enter your Phone No and Password", Toast.LENGTH_SHORT).show()
            }
        }

        tv.setOnClickListener {

            val x = Intent(this, MainActivity::class.java)
            startActivity(x)
        }

    }

    private fun readData(uniqueId: String, Password: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(uniqueId).get().addOnSuccessListener {

            //if user exist and password is correct
            val x = it.child("password").value.toString()

            if(it.exists() &&  x == Password){

                val intentHomePage = Intent(this, HomePage::class.java)

                startActivity(intentHomePage)
                finish()
            }

            else if(it.exists() && x != Password){
                Toast.makeText(this,"Wrong Password", Toast.LENGTH_SHORT).show()
            }

            else
            {
                Toast.makeText(this,"Wrong Phone No", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"FAILED ERROR IN DATABASE", Toast.LENGTH_SHORT).show()
        }
    }
}