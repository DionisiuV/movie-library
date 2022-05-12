package ro.valentin.movielibrary.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ro.valentin.movielibrary.R
import ro.valentin.movielibrary.presentation.auth.AuthActivity
import ro.valentin.movielibrary.presentation.main.MainActivity
import java.util.*
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: AppCompatActivity(R.layout.activity_splash) {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkIfUserIsAuthenticated()
    }

    private fun checkIfUserIsAuthenticated() {
            if(splashViewModel.isUserAuthenticated) {
                Timer().schedule(2000) {
                    goToMainActivity()
                }
            } else {
                Timer().schedule(2000) {
                    goToAuthActivity()
                }
            }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
    private fun goToAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}