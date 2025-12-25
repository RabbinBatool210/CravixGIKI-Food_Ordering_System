package com.example.CravixGIKI

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.CravixGIKI.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("CravixPrefs", MODE_PRIVATE)

        // CREATE ACCOUNT BUTTON
        binding.createAccountButton.setOnClickListener {

            val username = binding.userName.text.toString().trim()
            val email = binding.emailAddress.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all details 😒", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save user locally
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            Toast.makeText(this, "Account created successfully 😁", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // ALREADY HAVE ACCOUNT
        binding.alreadyhavebutton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // GOOGLE BUTTON DISABLED (Prototype)
        binding.googleButton.setOnClickListener {
            Toast.makeText(this, "Google Sign-Up disabled in prototype", Toast.LENGTH_SHORT).show()
        }
    }
}




































//old code

//package com.example.CravixGIKI
//
//import android.app.Activity
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import com.example.CravixGIKI.databinding.ActivitySignBinding
//import com.example.CravixGIKI.Model.UserModel
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.GoogleAuthProvider
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//
//class SignActivity : AppCompatActivity() {
//
//    private lateinit var email: String
//    private lateinit var password: String
//    private lateinit var username: String
//    private lateinit var auth: FirebaseAuth
//    private lateinit var database: DatabaseReference
//    private lateinit var googleSignInClint: GoogleSignInClient
//
//    private val binding: ActivitySignBinding by lazy {
//        ActivitySignBinding.inflate(layoutInflater)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
//        // initialize Firebase auth
//        auth = Firebase.auth
//        // initialize Firebase DataBase
//        database = Firebase.database.reference
//        // initialize Firebase DataBase
//        googleSignInClint = GoogleSignIn.getClient(this, googleSignInOptions)
//
//        binding.createAccountButton.setOnClickListener {
//            username = binding.userName.text.toString()
//            email = binding.emailAddress.text.toString().trim()
//            password = binding.password.text.toString().trim()
//
//            if (email.isEmpty() || password.isBlank() || username.isBlank()) {
//                Toast.makeText(this, "Please Fill  all the details", Toast.LENGTH_SHORT).show()
//            } else {
//                createAccount(email, password)
//            }
//        }
//        binding.alreadyhavebutton.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
//        binding.googleButton.setOnClickListener {
//            val signIntent = googleSignInClint.signInIntent
//            launcher.launch(signIntent)
//        }
//    }
//
//    // launcher gor google sign-in
//    private val launcher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                if (task.isSuccessful) {
//                    val account: GoogleSignInAccount? = task.result
//                    val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
//                    auth.signInWithCredential(credential).addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            Toast.makeText(this, "Sign-in SuccessFull 😁", Toast.LENGTH_SHORT).show()
//                            startActivity(Intent(this, MainActivity::class.java))
//                            finish()
//                        } else {
//                            Toast.makeText(this, "Sign in field 😒", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Sign in field 😒", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    private fun createAccount(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Toast.makeText(this, "Account Created successfully", Toast.LENGTH_SHORT).show()
//                saveUserData()
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            } else {
//                Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
//                Log.d("Account", "createAccount:  Failure", task.exception)
//            }
//        }
//    }
//
//    private fun saveUserData() {
//        // retrieve data from input filed
//        username = binding.userName.text.toString()
//        email = binding.emailAddress.text.toString().trim()
//        password = binding.password.text.toString().trim()
//
//        val user = UserModel(username, email, password)
//        val userId = FirebaseAuth.getInstance().currentUser!!.uid
//        // save data to Firebase data base
//        database.child("user").child(userId).setValue(user)
//    }
//}