package com.dicedev.thebigchampion.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicedev.thebigchampion.FirebaseRepository
import com.dicedev.thebigchampion.models.Group
import com.dicedev.thebigchampion.utils.CollectionNames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: FirebaseRepository) : ViewModel() {


    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups = _groups.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getGroups()
        }
    }

    private fun getGroups() = viewModelScope.launch {
        try {
            repository.getDocuments(collectionName = CollectionNames.GROUPS).map { querySnapshot ->
                querySnapshot.documents.map { Group(id = it.id, name = it.get("name") as String) }
            }.distinctUntilChanged().collect { listOfGroups ->
                if (listOfGroups.isNotEmpty()) {
                    _groups.value = listOfGroups
                }
            }
        } catch (exception: Exception) {
            Log.d("FB Firestore", "getGroups: ${exception.message}")
        }
    }

}