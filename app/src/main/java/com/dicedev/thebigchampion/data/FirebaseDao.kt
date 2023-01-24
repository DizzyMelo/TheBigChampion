package com.dicedev.thebigchampion.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseDao @Inject constructor(private val firestore: FirebaseFirestore) {
    fun addDocument(collectionName: String, document: HashMap<String, Any>): Task<DocumentReference> {
        return firestore.collection(collectionName).add(document)
    }

    fun getCollection(collectionName: String) {
        firestore.collection(collectionName).get()
    }
}