package ro.valentin.movielibrary.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.common.SignInButton
import ro.valentin.movielibrary.R

class AuthActivity : AppCompatActivity(R.layout.activity_auth) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSignInButton()
    }

    private fun initSignInButton() {
       findViewById<SignInButton>(R.id.sign_in_button).setOnClickListener {
            Toast.makeText(this, "Button pressed", Toast.LENGTH_SHORT).show()
        }
    }
}