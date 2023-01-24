package com.dicedev.thebigchampion.models

data class Group(
    val id: String? = null,
    val name: String,
    val image: String? = null,
    val members: List<String> = emptyList()
) {
    fun toMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to this.id.toString(),
            "name" to this.name,
            "image" to this.image.toString(),
            "members" to members
        )
    }
}
