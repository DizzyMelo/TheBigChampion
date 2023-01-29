package com.dicedev.thebigchampion.reposiroty

import com.dicedev.thebigchampion.data.FirebaseDao
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val dao: FirebaseDao) {
    fun insertDocument(
        collectionName: String,
        document: HashMap<String, Any>
    ): Task<DocumentReference> {
        return dao.addDocument(collectionName, document)
    }

    fun insertReferenceValueToArray(
        collectionName: String,
        documentId: String,
        fieldName: String,
        value: String
    ): Task<Void> {
        return dao.addReferenceValueToArray(collectionName, documentId, fieldName, value)
    }

    fun getDocuments(collectionName: String): Flow<QuerySnapshot> {
        return dao.getDocumentsFromCollection(collectionName)
    }

    fun getDocumentByFieldName(
        collectionName: String,
        fieldName: String,
        value: String
    ): Task<QuerySnapshot> {
        return dao.getDocumentByField(collectionName, fieldName, value)
    }
}