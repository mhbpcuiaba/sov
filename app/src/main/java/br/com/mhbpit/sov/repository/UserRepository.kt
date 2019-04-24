package br.com.mhbpit.sov.repository

import com.google.firebase.firestore.FirebaseFirestore
import android.support.v7.widget.AppCompatEditText
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot


class UserRepository() {


    companion object {
        private val TAG = "[UR]"
        private val COLLECTION = "perfis"
        val db =  FirebaseFirestore.getInstance()
        var data: MutableMap<String, Any>?  = null

        fun docReference(userId: String, edt_nickname: AppCompatEditText) {
            // [START doc_reference]
            val userRef = db.collection(COLLECTION).document(userId)

            userRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot>() {
                    if (it.isSuccessful()) {
                        val document: DocumentSnapshot = it.getResult()!!
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            this.data = document.getData()
                            edt_nickname.setText(this.data!!.get("nickname").toString())
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", it.getException());
                    }
            })

        }

        fun updateDocument(userId: String,  data: Map<String, Any>) {
            // [START update_document]
            val userRef = db.collection(COLLECTION).document(userId)
            userRef
                    .update(data)
                    .addOnSuccessListener { Log.d(TAG, "Usuario atualizado com sucesso!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Erro ao atualizar o usuario", e) }
        }

        fun setDocument(userId: String,  data: Map<String, Any>) {
            db.collection(COLLECTION).document(userId)
                    .set(data)
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        }
    }


}