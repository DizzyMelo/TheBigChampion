package com.dicedev.thebigchampion

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheBigChampionApplication: Application() {
    companion object {
        var activeUserId: String? = null
    }
}