package com.dicedev.thebigchampion.screens.group.add_player

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.dicedev.thebigchampion.reposiroty.FirebaseRepository
import com.dicedev.thebigchampion.utils.CollectionNames
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPlayersToGroupViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {

    private val playersAddedToGroup: SnapshotStateList<String> = mutableStateListOf()
    val playersAdded get() = playersAddedToGroup

    fun addPlayerToGroup(groupId: String, email: String) {

        repository.getDocumentByFieldName(CollectionNames.USERS, "email", email)
            .addOnSuccessListener {  }.addOnFailureListener {  }

        repository.insertReferenceValueToArray(
            collectionName = CollectionNames.GROUPS,
            documentId = groupId,
            fieldName = "players",
            value = "${CollectionNames.USERS}/"
        ).addOnSuccessListener {
            Log.d("FB ADDED", "addPlayerToGroup: player added")
            playersAddedToGroup.add("Player")
        }
    }
}