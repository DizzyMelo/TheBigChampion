package com.dicedev.thebigchampion.data

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseDao @Inject constructor(private val firestore: FirebaseFirestore) {
    fun addDocument(document: HashMap<String, Any>) {
        firestore.collection("users").add(document)
    }

    fun getCollection(collectionName: String) {
        firestore.collection(collectionName).get()
    }
}