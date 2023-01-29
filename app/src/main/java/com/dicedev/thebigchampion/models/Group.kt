package com.dicedev.thebigchampion.models

data class Group(
    val id: String? = null,
    val name: String,
    val ownerId: String,
    val image: String? = null,
    val players: List<String> = emptyList(),
    val games: List<String> = emptyList(),
): HasToMap {

    override fun toMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to this.id.toString(),
            "name" to this.name,
            "ownerId" to this.ownerId,
            "image" to this.image.toString(),
            "players" to players,
            "games" to games,
        )
    }
}
