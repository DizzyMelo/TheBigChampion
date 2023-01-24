package com.dicedev.thebigchampion.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseDao @Inject constructor(private val firestore: FirebaseFirestore) {
    fun addDocument(
        collectionName: String,
        document: HashMap<String, Any>
    ): Task<DocumentReference> {
        return firestore.collection(collectionName).add(document)
    }

    fun getDocumentsFromCollection(collectionName: String): Flow<QuerySnapshot> {
        return firestore.collection(collectionName).snapshotFlow()
    }

    private fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
        val listenerRegistration = addSnapshotListener { value, error ->
            if (error != null) {
                close()
                return@addSnapshotListener
            }
            if (value != null)
                trySend(value)
        }
        awaitClose {
            listenerRegistration.remove()
        }
    }
}