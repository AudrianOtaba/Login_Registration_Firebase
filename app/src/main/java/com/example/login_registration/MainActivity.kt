package com.example.login_registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var EdtEmail : EditText
    private lateinit var EdtPassword : EditText
    lateinit var EdtConPassword : EditText
    private lateinit var BtnSign : Button
    lateinit var TvDirectlogin : TextView
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EdtEmail=findViewById(R.id.etEmailAddress)
        EdtPassword=findViewById(R.id.etSPassword)
        EdtConPassword=findViewById(R.id.etSConfPassword)
        BtnSign=findViewById(R.id.btnSSigned)
        TvDirectlogin=findViewById(R.id.tvRedirectSignUp)
        auth= Firebase.auth


        BtnSign.setOnClickListener {
            SignUpUser()

        }
        TvDirectlogin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun SignUpUser(){
        val email=EdtEmail.text.toString()
        val pass=EdtPassword.text.toString()
        val confirmPass=EdtConPassword.text.toString()

        if (email.isBlank()|| pass.isBlank()|| confirmPass.isBlank() ){
            Toast.makeText(this,"Please email and password cant be blank",Toast.LENGTH_LONG).show()
            return
        } else if (pass!=confirmPass){
            Toast.makeText(this,"Password do not match",Toast.LENGTH_LONG).show()
            return

        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
            if (it.isSuccessful){
               Toast.makeText(this,"Signed Successful",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Failed to create",Toast.LENGTH_LONG).show()
            }
        }

    }
}