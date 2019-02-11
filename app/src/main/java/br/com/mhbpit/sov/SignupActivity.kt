package br.com.mhbpit.sov

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatEditText
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
//    lateinit var mDatabase: DatabaseReference
    val db = FirebaseFirestore.getInstance()
    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

//        mDatabase = FirebaseDatabase.getInstance().getReference("Usuarios")

        btn_submit.setOnClickListener{
            register()
        }
    }

    private fun register() {
        val edt_email = findViewById<View>(R.id.edt_email) as AppCompatEditText
        val edt_password = findViewById<View>(R.id.edt_password) as AppCompatEditText
        val edt_password_confirmation = findViewById<View>(R.id.edt_password_confirmation) as AppCompatEditText
        val txt_layout_email = findViewById<View>(R.id.txt_layout_email) as TextInputLayout
        val txt_layout_password = findViewById<View>(R.id.txt_layout_password) as TextInputLayout
        val txt_layout_password_confirmation = findViewById<View>(R.id.txt_layout_password_confirmation) as TextInputLayout

        if (edt_email.text!!.length == 0) {
            txt_layout_email.isErrorEnabled = true
            txt_layout_email.error = "Preencha com seu e-mail: "
        } else {
            txt_layout_email.isErrorEnabled = false
        }


        if (edt_password.text!!.length == 0) {
            txt_layout_password.isErrorEnabled = true
            txt_layout_password.error = "Preencha com sua senha."
        } else {
            txt_layout_password.isErrorEnabled = false
        }

        if (edt_password_confirmation.text?.length == 0) {
            txt_layout_password_confirmation.isErrorEnabled = true
            txt_layout_password_confirmation.error = "Preencha com sua confirmação de senha."
        } else {
            txt_layout_password_confirmation.isErrorEnabled = false
        }

        if (edt_password_confirmation.text?.length != 0 &&
                edt_password.text!!.length != 0 &&
                edt_password_confirmation.text?.toString()?.equals(edt_password.text?.toString())!!) {
            txt_layout_password_confirmation.isErrorEnabled = false
            txt_layout_password.isErrorEnabled = false
        } else {
            txt_layout_password_confirmation.isErrorEnabled = true
            txt_layout_password.isErrorEnabled = true
            txt_layout_password_confirmation.error = "Password e Confirmação de Pasword estão diferentes!"
            txt_layout_password.error = "Password e Confirmação de Pasword estão diferentes!"

        }

        if (!txt_layout_password_confirmation.isErrorEnabled && !txt_layout_password.isErrorEnabled && !txt_layout_email.isErrorEnabled) {
            Toast.makeText(this, "tenta submeter os dados para firebase", Toast.LENGTH_LONG).show()
            val password = edt_password.text?.toString()
            val password_confirmation = edt_password_confirmation.text?.toString()
            val email = edt_email.text?.toString()

            if (!password!!.isEmpty() && !password_confirmation!!.isEmpty() && !email!!.isEmpty()) {
                Toast.makeText(this, "submeteu os dados para firebase", Toast.LENGTH_LONG).show()

                try {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this@SignupActivity, {
                        task ->
                        Log.i("register_firebase","chegou resposta do firebase")
                        Toast.makeText(this@SignupActivity, "resposta do firebase taki", Toast.LENGTH_LONG).show()
                        if ( task.isSuccessful) {
                            Log.i("register_firebase","sucesso registro firebase")
                            val user = mAuth.currentUser
                            val uid = user!!.uid.toString()
                            //cria um usuario dda collections usuarios fo firebase cloud firestore
                            val userFS = HashMap<String, Any>()
                            userFS["uid"] = uid;
                            userFS["email"] = user!!.email.toString();

                            db.collection("usuarios")
                                    .add(userFS)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d("_FIRESTORE_", "DocumentSnapshot added with ID: " + documentReference.id)
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w("_FIRESTORE_", "Error adding document", e)
                                    }

                            Toast.makeText(this@SignupActivity, " Successfully signed in :)", Toast.LENGTH_LONG).show()
                        } else {

                            Log.i("register_firebase","falha registro firebase-INIT2: ")
//                            Log.i("register_firebase", task.getResult().user.email)
                            Log.i("register_firebase","falha registro firebase-END2: ")
                            Toast.makeText(this@SignupActivity, "Erro ao tentar efetuar o cadastro. Entre em contato com o suporte. 8765-0987, result: " + task.getException()?.message, Toast.LENGTH_LONG).show();
                        }


                    })
                }catch (exception: Exception) {
                    Log.i("register_firebase","erro: " + exception.toString())
                }

            } else {
                Toast.makeText(this, "Corrija os erros do formulário.", Toast.LENGTH_LONG).show();
            }

        }
    }
}

/*
Cv form

( Step 1: dados pessoais)
        Nome
        Sobrenome
        Data de nascimento
        Gênero
        Portador de deficiência física?


(Step 2: contatos)
        E-mail
        Endereço (integração com o google maps é uma tendência)
        Celular
        Telefone fixo


(Step 3: Formação)

        Tem ensino superior? (radio button, sim ou não. Se sim, abre demais campos).
            Graduação (tecnólogo, bacharelado, etc).
            Status (completo, cursando).
            Instituição (apenas para ensino superior. Lista de universidades)
            Curso (apenas para ensino superior. Lista de cursos).
            Início da graduação
            Término

(Step 4: Experiência profissional)

        Tem experiência? (sim ou não. Se sim, abre demais campos).
        Nome da empresa (aberto).
        Cargo (aberto).
        Início
        Término
        Principais atividades
        Idioma + nível (lista suspensa)

( Step 5: Links de perfis em redes sociais )

        LinkedIn URL
        Twitter URL
        GitHub URL
        Portifolio URL
        Other website

       btn-save

 */