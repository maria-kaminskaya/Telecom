package com.telecom.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.telecom.MainActivity
import com.telecom.R
import kotlinx.android.synthetic.main.activity_signup.*

class SingnupActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context?) = Intent(context, SingnupActivity::class.java)
    }

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance().reference
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener {
        val user = firebaseAuth.currentUser
        if (user != null) {
            startActivity(MainActivity.newIntent(this))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }

//    fun onSignup(v: View) {
//        if (!emailET.text.toString().isNullOrEmpty() && !passwordET.text.toString().isNullOrEmpty()) {
//            firebaseAuth.createUserWithEmailAndPassword(emailET.text.toString(), passwordET.text.toString())
//                .addOnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        Toast.makeText(this, "SignUp error ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
//                    } else {
//                        val email = emailET.text.toString()
//                        val name = nameET.text.toString()
//                        val userId = firebaseAuth.currentUser?.uid ?: ""
//                        val user = Client(userId, name, surname, email, balance, phone, "")
//                        firebaseDatabase.child(DATA_USERS).child(userId).setValue(user)
//                    }
//                }
//            val user = FirebaseUser()
//            firebaseAuth.updateCurrentUser(user)
//        }
//    }


}
