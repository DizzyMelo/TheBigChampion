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

    fun addPlayerToGroup(groupId: String, email: String, onFailureCallback: () -> Unit) {
        repository.getDocumentByFieldName(
            collectionName = CollectionNames.USERS,
            fieldName = "email",
            value = email
        )
            .addOnSuccessListener { task ->
                if (task.documents.isNotEmpty()) {
                    insertReferenceToArray(
                        groupId,
                        task.documents.first().get("userId") as String
                    )
                } else {
                    onFailureCallback.invoke()
                }
            }
            .addOnFailureListener { onFailureCallback.invoke() }
    }

    private fun insertReferenceToArray(groupId: String, playerId: String) {
        repository.insertReferenceValueToArray(
            collectionName = CollectionNames.GROUPS,
            documentId = groupId,
            fieldName = "players",
            value = "${CollectionNames.USERS}/$playerId"
        ).addOnSuccessListener {
            Log.d("FB ADDED", "addPlayerToGroup: player added")
            playersAddedToGroup.add("Player")
        }
    }
}