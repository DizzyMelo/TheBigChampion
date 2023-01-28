package com.dicedev.thebigchampion.screens.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.TheBigChampionApplication
import com.dicedev.thebigchampion.models.Group
import com.dicedev.thebigchampion.reposiroty.FirebaseRepository
import com.dicedev.thebigchampion.utils.CollectionNames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(private val repository: FirebaseRepository) : ViewModel() {
    fun createGroup(
        name: String,
        onSuccessAction: () -> Unit = {},
        onFailureAction: () -> Unit = {}
    ) = viewModelScope.launch {
        try {
            repository.insertDocument(
                collectionName = CollectionNames.GROUPS,
                document = Group(
                    name = name,
                    ownerId = TheBigChampionApplication.activeUserId.toString(),
                    players = listOf("users/${TheBigChampionApplication.activeUserId}")
                ).toMap()
            ).addOnSuccessListener {
                onSuccessAction.invoke()
            }.addOnFailureListener {
                onFailureAction.invoke()
            }
        } catch (exception: Exception) {
            onFailureAction.invoke()
        }
    }
}