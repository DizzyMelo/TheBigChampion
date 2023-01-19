package com.dicedev.thebigchampion.screens.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val signInLoading: MutableState<Boolean> = mutableStateOf(false)
    fun signInWithEmailAndPassword(email: String, password: String, callback: () -> Unit = {}) =
        viewModelScope.launch {
            try {
                signInLoading.value = true
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            callback.invoke()
                        } else {
                            Log.d(
                                "FB AUTh",
                                "signInWithEmailAndPassword: email or password incorrect"
                            )
                        }
                    }
            } catch (exception: Exception) {
                Log.d("FB AUTh", "signInWithEmailAndPassword: ${exception.message}")
            } finally {
                signInLoading.value = false
            }
        }
}