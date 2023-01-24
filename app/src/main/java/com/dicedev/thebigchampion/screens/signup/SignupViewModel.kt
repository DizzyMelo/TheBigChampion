package com.dicedev.thebigchampion.screens.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.data.FirebaseDao
import com.dicedev.thebigchampion.models.User
import com.dicedev.thebigchampion.utils.CollectionNames
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDao: FirebaseDao
) : ViewModel() {
    fun signupWithEmailAndPassword(email: String, password: String, onSuccessCallback: () -> Unit) =
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User(
                            id = task.result.user?.uid,
                            name = "test",
                            email = email,
                            photo = "no-photo"
                        ).toMap()
                        firebaseDao.addDocument(
                            collectionName = CollectionNames.USERS,
                            document = user
                        ).run {
                            onSuccessCallback.invoke()
                        }
                    } else {
                        Log.d(
                            "SIGN UP",
                            "signupWithEmailAndPassword: something went wrong trying to create user"
                        )
                    }
                }
        }
}