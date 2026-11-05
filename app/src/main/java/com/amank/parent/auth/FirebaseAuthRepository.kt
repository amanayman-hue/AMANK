package com.amank.parent.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String
    ): AuthResult {

        return try {
            require(email.contains("@")) {
                "البريد الإلكتروني غير صحيح"
            }

            require(password.length >= 😎 {
                "كلمة المرور يجب ألا تقل عن 8 أحرف"
            }

            val result = auth
                .createUserWithEmailAndPassword(
                    email.trim(),
                    password
                ).await()

            val user = result.user
                ?: return AuthResult.Failure(
                    "تعذر إنشاء الحساب"
                )

            user.sendEmailVerification().await()

            AuthResult.Success(
                user.uid,
                user.email
            )

        } catch (e: Exception) {
            AuthResult.Failure(
                e.localizedMessage ?: "حدث خطأ أثناء التسجيل"
            )
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): AuthResult {

        return try {
            val result = auth
                .signInWithEmailAndPassword(
                    email.trim(),
                    password
                ).await()

            val user = result.user
                ?: return AuthResult.Failure(
                    "بيانات الدخول غير صحيحة"
                )

            if (!user.isEmailVerified) {
                auth.signOut()

                return AuthResult.Failure(
                    "يرجى تأكيد البريد الإلكتروني"
                )
            }

            AuthResult.Success(
                user.uid,
                user.email
            )

        } catch (e: Exception) {
            AuthResult.Failure(
                e.localizedMessage ?: "فشل تسجيل الدخول"
            )
        }
    }
}
إنشاء
اكتب إلى ايمن احميد
