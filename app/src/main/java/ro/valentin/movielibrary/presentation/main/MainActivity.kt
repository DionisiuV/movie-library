package ro.valentin.movielibrary.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.google.android.gms.common.SignInButton
import dagger.hilt.android.AndroidEntryPoint
import ro.valentin.movielibrary.R
import ro.valentin.movielibrary.core.Constants
import ro.valentin.movielibrary.core.Utils.Companion.hide
import ro.valentin.movielibrary.core.Utils.Companion.show
import ro.valentin.movielibrary.domain.model.Response
import ro.valentin.movielibrary.presentation.auth.AuthActivity
import ro.valentin.movielibrary.presentation.auth.AuthViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var loaderProgressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressBar()
        getAuthState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.signOut -> {
                signOut()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        viewModel.firebaseSignOut().observe(this) {  response ->
            when(response) {
                is Response.Loading -> loaderProgressBar.show()
                is Response.Success -> loaderProgressBar.hide()
                is Response.Error -> {
                    loaderProgressBar.hide()
                    Log.d(Constants.LOG_TAG, response.error)
                }
            }

        }
    }

    private fun initProgressBar() {
        loaderProgressBar = findViewById(R.id.loadingProgressBar)
    }

    private fun getAuthState() {
        viewModel.authStateListener().observe(this) { state ->
            if(state) {
                goToAuthActivity()
            }
        }
    }

    private fun goToAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}