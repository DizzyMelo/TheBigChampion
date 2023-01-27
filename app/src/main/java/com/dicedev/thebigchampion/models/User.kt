package com.dicedev.thebigchampion.models

data class User(
    var id: String? = null,
    var userId: String? = null,
    var name: String? = null,
    var email: String? = null,
    var photo: String? = null
) {
    fun toMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to this.id.toString(),
            "userId" to this.userId.toString(),
            "name" to this.name.toString(),
            "email" to this.email.toString(),
            "photo" to this.photo.toString(),
        )
    }
}

