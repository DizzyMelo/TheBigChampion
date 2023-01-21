package com.dicedev.thebigchampion.data.stubs

import com.dicedev.thebigchampion.models.Group

object GroupStubs {

    fun getPopulatedGroups(): List<Group> = listOf(
        Group(id = "", name = "the troop", image = "no-image", members = emptyList()),
        Group(id = "", name = "the besties", image = "no-image", members = emptyList()),
        Group(id = "", name = "wakanda", image = "no-image", members = emptyList()),
        Group(id = "", name = "Utah game", image = "no-image", members = emptyList()),
        Group(id = "", name = "BYUI nags", image = "no-image", members = emptyList()),
    )

    fun getEmptyListOfGroups(): List<Group> = emptyList()
}