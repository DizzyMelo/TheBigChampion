package com.dicedev.thebigchampion.screens.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.TheBigChampionApplication
import com.dicedev.thebigchampion.reposiroty.FirebaseRepository
import com.dicedev.thebigchampion.utils.CollectionNames
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: FirebaseRepository) : ViewModel() {
    val signInLoading: MutableState<Boolean> = mutableStateOf(false)
    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccessCallback: () -> Unit = {},
        onFailureCallback: () -> Unit = {}
    ) =
        viewModelScope.launch {
            try {
                signInLoading.value = true
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { task ->
                        val fieldName = "userId"
                        repository.getDocumentByFieldName(
                            CollectionNames.USERS,
                            fieldName,
                            task.user?.uid.toString()
                        ).addOnSuccessListener {
                            Log.d("FB GET ID", "signInWithEmailAndPassword: ${it.documents.first().data}")
                            TheBigChampionApplication.activeUserId = task.user?.uid
                            onSuccessCallback.invoke()
                        }.addOnFailureListener {
                            onFailureCallback.invoke()
                        }
                    }.addOnFailureListener {
                        onFailureCallback.invoke()
                    }
            } catch (exception: Exception) {
                Log.d("FB AUTh", "signInWithEmailAndPassword: ${exception.message}")
            } finally {
                signInLoading.value = false
            }
        }
}