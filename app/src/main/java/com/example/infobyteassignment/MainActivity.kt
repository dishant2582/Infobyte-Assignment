package com.example.infobyteassignment


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val SignUpBtn = findViewById<Button>(R.id.SignUpBtn)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<TextInputEditText>(R.id.etconfirmPassword)
        val etId = findViewById<TextInputEditText>(R.id.etId)
        val tv = findViewById<TextView>(R.id.tv1)

        SignUpBtn.setOnClickListener {

            val name = etName.text.toString()
            val email = etMail.text.toString()
            val password = etPassword.text.toString()
            val confirmpassword = etConfirmPassword.text.toString()
            val uniqueId = etId.text.toString()

            if(uniqueId.isEmpty() || password.isEmpty() || email.isEmpty() || name.isEmpty()) {

                Toast.makeText(this, "Enter FULL Details", Toast.LENGTH_SHORT).show()
            }


            else {

                val user = User(name, email, password, uniqueId)

                database = FirebaseDatabase.getInstance().getReference("Users")

                database.child(uniqueId).get().addOnSuccessListener {

                    if(it.exists()){
                        Toast.makeText(this, "Phone No Already Exist Try Different One", Toast.LENGTH_SHORT).show()
                    }

                    else if (password != confirmpassword){
                        Toast.makeText(this, "Confirm Password is wrong", Toast.LENGTH_SHORT)
                            .show()
                    }

                    else{

                        database.child(uniqueId).setValue(user).addOnSuccessListener {

                            Toast.makeText(this, "User Registerd Succesfully", Toast.LENGTH_SHORT)
                                .show()

                            etName.text?.clear()
                            etMail.text?.clear()
                            etPassword.text?.clear()
                            etId.text?.clear()
                            etConfirmPassword.text?.clear()

                            val x = Intent(this, HomePage::class.java)
                            startActivity(x)
                            finish()


                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }

                    }


                }

            }
        }

        tv.setOnClickListener {

            val x = Intent(this, Login::class.java)
            startActivity(x)
        }

    }
}


