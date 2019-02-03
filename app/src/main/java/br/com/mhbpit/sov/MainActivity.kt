package br.com.mhbpit.sov

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.mhbpit.sov.activemodel.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_login.setOnClickListener {
            validateForm()
        }
        btn_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    fun validateForm() {
        if (edt_email.text?.length== 0) {
            txt_layout_email.isErrorEnabled = true
            txt_layout_email.error = "Preencha com seu e-mail: "
        } else {
            txt_layout_email.isErrorEnabled = false
        }


        if (edt_password.text?.length == 0) {
            txt_layout_password.isErrorEnabled = true
            txt_layout_password.error = "Preencha com seu e-mail"
        } else {
            txt_layout_password.isErrorEnabled = false
        }

        if (!txt_layout_password.isErrorEnabled && !txt_layout_email.isErrorEnabled) {
            val password = edt_password.text?.toString()
            val email = edt_email.text?.toString()
//            mAuth.signInWithEmailAndPassword(email!!, password!!).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    val intent = Intent(this, HomeActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(this, "Login ou senha inválido!", Toast.LENGTH_LONG).show()
//                }
//            }

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }
    }
}

//https://material.io/guidelines/style/color.html#color-color-palette
//https://material.io/color/#!/?view.left=0&view.right=1&primary.color=8BC34A&secondary.color=689F38&primary.text.color=000000
//https://material.io/icons/

/*
https://erikcaffrey.github.io/ANDROID-kotlin-arch-components/

https://developer.android.com/topic/libraries/architecture/index.html

https://developer.android.com/topic/libraries/architecture/adding-components.html

https://github.com/willowtreeapps/spruce-android

https://willowtreeapps.com/ideas/introducing-spruce

https://github.com/pchmn/MaterialChipsInput


**login screen design
http://www.androidcodefinder.com/blog/2018/07/30/awesome-login-screen-design-using-constraint-layout-in-android-studio/
 */