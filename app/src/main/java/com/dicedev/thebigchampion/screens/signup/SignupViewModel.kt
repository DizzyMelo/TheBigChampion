package com.dicedev.thebigchampion.screens.signup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.TheBigChampionApplication
import com.dicedev.thebigchampion.data.FirebaseDao
import com.dicedev.thebigchampion.models.User
import com.dicedev.thebigchampion.utils.CollectionNames
import com.dicedev.thebigchampion.utils.Utils
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDao: FirebaseDao
) : ViewModel() {
    val isLoading: MutableState<Boolean> = mutableStateOf(false)

    fun signupWithEmailAndPassword(
        email: String,
        password: String,
        onSuccessCallback: () -> Unit,
        onFailureCallback: () -> Unit
    ) =
        viewModelScope.launch {
            isLoading.value = true
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { task ->
                    createFirestoreUser(task, email, onSuccessCallback, onFailureCallback)
                }.addOnFailureListener {
                    onFailureCallback.invoke()
                }.addOnCompleteListener {
                    isLoading.value = false
                }
        }

    private fun createFirestoreUser(
        task: AuthResult,
        email: String,
        onSuccessCallback: () -> Unit,
        onFailureCallback: () -> Unit
    ) {
        val user = User(
            userId = task.user?.uid,
            name = Utils.getUserNameFromEmail(email),
            email = email,
            photo = "no-photo"
        ).toMap()
        firebaseDao.addDocument(
            collectionName = CollectionNames.USERS,
            document = user
        ).addOnSuccessListener {
            TheBigChampionApplication.activeUserId = it.id
            onSuccessCallback.invoke()
        }.addOnFailureListener {
            onFailureCallback.invoke()
        }
    }
}