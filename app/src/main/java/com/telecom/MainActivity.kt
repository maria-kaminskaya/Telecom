package com.telecom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.telecom.databinding.ActivityMainBinding

//
//class MainActivity : AppCompatActivity() {
//
//    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val currentUser = mAuth.currentUser
//        //updateUI(currentUser)
//    }

    class MainActivity : AppCompatActivity() {

        companion object {
            fun newIntent(context: Context?) = Intent(context, MainActivity::class.java)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            @Suppress("UNUSED_VARIABLE")
            val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//            val drawerLayout = binding.drawerLayout
//            val navController = this.findNavController(R.id.main)

//        NavigationUI.setupActionBarWithNavController(this, navController)
//            NavigationUI.setupWithNavController(binding.navView, navController) // to display the navigation drawer.
//            NavigationUI.setupWithNavController(binding.bottomMenuNavigation, navController)

//            val appBarConfiguration = AppBarConfiguration(navController.graph) // For back buttons
//            NavigationUI.setupActionBarWithNavController(this , navController, appBarConfiguration)

//            getSupportFragmentManager().addOnBackStackChangedListener(onBackPressed());
            //Handle when activity is recreated like on orientation Change
        }

        override fun onSupportNavigateUp(): Boolean { //This method is called when the up button is pressed. Just the pop back stack.
            supportFragmentManager.popBackStack()
            return true
        }

    }



//    mAuth.signInWithEmailAndPassword(email, password)
//    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//     overraide fun onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "signInWithEmail:success");
//                FirebaseUser user = mAuth.getCurrentUser();
//                updateUI(user);
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                    Toast.LENGTH_SHORT).show();
//                updateUI(null);
//            }
//
//            // ...
//        }
//    });

//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    if (user != null) {
//        // Name, email address, and profile photo Url
//        String name = user.getDisplayName();
//        String email = user.getEmail();
//        Uri photoUrl = user.getPhotoUrl();
//
//        // Check if user's email is verified
//        boolean emailVerified = user.isEmailVerified();
//
//        // The user's ID, unique to the Firebase project. Do NOT use this value to
//        // authenticate with your backend server, if you have one. Use
//        // FirebaseUser.getIdToken() instead.
//        String uid = user.getUid();
//    }
//}
