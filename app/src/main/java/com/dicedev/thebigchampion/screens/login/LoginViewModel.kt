package com.dicedev.thebigchampion.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.TheBigChampionApplication
import com.dicedev.thebigchampion.reposiroty.FirebaseRepository
import com.dicedev.thebigchampion.utils.CollectionNames
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: FirebaseRepository) : ViewModel() {
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccessCallback: () -> Unit = {},
        onFailureCallback: () -> Unit = {}
    ) =
        viewModelScope.launch {
            isLoading.value = true
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { task ->
                    getFirestoreUser(task, onSuccessCallback, onFailureCallback)
                }.addOnFailureListener {
                    onFailureCallback.invoke()
                }.addOnCompleteListener {
                    isLoading.value = false
                }
        }

    private fun getFirestoreUser(
        task: AuthResult,
        onSuccessCallback: () -> Unit,
        onFailureCallback: () -> Unit
    ) {
        val fieldName = "userId"
        repository.getDocumentByFieldName(
            CollectionNames.USERS,
            fieldName,
            task.user?.uid.toString()
        ).addOnSuccessListener {
            TheBigChampionApplication.activeUserId =
                it.documents.first().data?.get("userId")
                    ?.toString()
            onSuccessCallback.invoke()
        }.addOnFailureListener {
            onFailureCallback.invoke()
        }
    }
}