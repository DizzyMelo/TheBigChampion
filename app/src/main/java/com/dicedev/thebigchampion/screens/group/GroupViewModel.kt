package com.dicedev.thebigchampion.screens.group

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.data.FirebaseDao
import com.dicedev.thebigchampion.models.Group
import com.dicedev.thebigchampion.utils.CollectionNames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(private val firebaseDao: FirebaseDao) : ViewModel() {
    fun createGroup(name: String, onSuccessAction: () -> Unit = {}) = viewModelScope.launch {
        try {
            firebaseDao.addDocument(
                collectionName = CollectionNames.GROUPS,
                document = Group(name = name).toMap()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccessAction.invoke()
                } else {
                    Log.d("FB Firestore", "createGroup: the task failed")
                }
            }
        } catch (exception: Exception) {
            Log.d("FB Firestore", "createGroup: Exception ${exception.message}")
        }
    }
}