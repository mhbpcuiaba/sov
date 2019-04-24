package br.com.mhbpit.sov.activemodel.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatEditText
import android.util.Log
import android.util.Log.i
import android.view.View
import br.com.mhbpit.sov.R
import br.com.mhbpit.sov.repository.UserRepository
import br.com.mhbpit.sov.util.SaveSharedPreference
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnSave.setOnClickListener{
            saveProfile()
        }

    }

    override fun onResume() {
        super.onResume()
        val userUID = SaveSharedPreference.getUserUID(applicationContext)
        val edt_nickname = findViewById<View>(R.id.edt_nickname) as AppCompatEditText
        UserRepository.docReference(userUID, edt_nickname)
    }

    private fun saveProfile() {
        val edt_mobile = findViewById<View>(R.id.edt_mobile) as AppCompatEditText
        val edt_nickname = findViewById<View>(R.id.edt_nickname) as AppCompatEditText
        val userUID = SaveSharedPreference.getUserUID(applicationContext)
        val userFS = HashMap<String, Any>()
        userFS["celular"] = edt_mobile.text!!.toString()
        userFS["nickname"] = edt_nickname.text!!.toString()
        Log.d("TEST_DB", " tenta atualizar dados do profile")
        try {
            Log.d("TEST_DB", " pegou a userRef")
            UserRepository.setDocument(userUID, userFS)
            Log.d("TEST_DB", " atualizou")
        } catch (e: Throwable) {
            Log.d("TEST_DB", " erro ao atualizar" + e.message)
        }
    }
}
