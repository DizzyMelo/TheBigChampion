package com.dicedev.thebigchampion.screens.home

import androidx.lifecycle.ViewModel
import com.dicedev.thebigchampion.data.FirebaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseDao: FirebaseDao): ViewModel() {

}