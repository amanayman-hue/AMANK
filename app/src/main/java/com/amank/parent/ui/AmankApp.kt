package com.amank.parent.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amank.parent.auth.AuthRepository
import com.amank.parent.auth.AuthResult
import kotlinx.coroutines.launch

@Composable
fun AmankApp(repository: AuthRepository) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var loggedIn by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            "AMANK",
            style = MaterialTheme.typography.headlineLarge
        )

        Text("لوحة ولي الأمر")

        if (!loggedIn) {

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("البريد الإلكتروني") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("كلمة المرور") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    scope.launch {
                        when (
                            val result = repository.register(
                                email,
                                password
                            )
                        ) {
                            is AuthResult.Success ->
                                message = "تحقق من بريدك الإلكتروني"

                            is AuthResult.Failure ->
                                message = result.message
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("إنشاء حساب")
            }

            Button(
                onClick = {
                    scope.launch {
                        when (
                            val result = repository.signIn(
                                email,
                                password
                            )
                        ) {
                            is AuthResult.Success -> {
                                loggedIn = true
                                message = ""
                            }

                            is AuthResult.Failure ->
                                message = result.message
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تسجيل الدخول")
            }

        } else {

            Text("مرحباً بك في AMANK")

            Text("سيتم ربط إدارة الأجهزة في المرحلة التالية.")
        }

        Text(message)
    }
}
