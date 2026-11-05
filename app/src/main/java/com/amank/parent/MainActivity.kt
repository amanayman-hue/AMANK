package com.amank.parent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.google.firebase.auth.FirebaseAuth
import com.amank.parent.auth.FirebaseAuthRepository
import com.amank.parent.ui.AmankApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = FirebaseAuthRepository(
            FirebaseAuth.getInstance()
        )

        setContent {
            MaterialTheme {
                AmankApp(repository)
            }
        }
    }
}
