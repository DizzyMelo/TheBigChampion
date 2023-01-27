package com.dicedev.thebigchampion.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.FirebaseRepository
import com.dicedev.thebigchampion.data.ScreenState
import com.dicedev.thebigchampion.models.Group
import com.dicedev.thebigchampion.utils.CollectionNames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: FirebaseRepository) : ViewModel() {

    val screenState: MutableState<ScreenState<List<Group>>> =
        mutableStateOf(ScreenState(loading = true))

    init {
        viewModelScope.launch {
            getGroups()
        }
    }

    private suspend fun getGroups() {
        repository.getDocuments(collectionName = CollectionNames.GROUPS).map { querySnapshot ->
            querySnapshot.documents.map { Group(id = it.id, name = it.get("name") as String) }
        }.distinctUntilChanged().collect { listOfGroups ->
            screenState.value = ScreenState(loading = false, groups = listOfGroups)
        }
    }

}