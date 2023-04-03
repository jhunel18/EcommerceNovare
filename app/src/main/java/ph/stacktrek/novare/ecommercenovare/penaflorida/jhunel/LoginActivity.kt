package ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.databinding.ActivityLoginBinding
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.utility.PreferenceUtility

class LoginActivity : AppCompatActivity() {

    private lateinit var registerUsernameData:String
    private lateinit var registerPasswordData:String

    private lateinit var binding: ActivityLoginBinding

    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        registerUsernameData= data!!.getStringExtra("register_username").toString()
        registerPasswordData = data.getStringExtra("register_password").toString()


        binding.usernametext.setText(registerUsernameData)
        binding.passwordtext.setText(registerPasswordData)

        Snackbar.make(binding.root,
            "Registered ${data.getStringExtra("register_username")}",
            Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val goToRegister = Intent(
                applicationContext,
                RegisterActivity::class.java
            )

            launchRegister.launch(goToRegister)

        }

        binding.loginButton.setOnClickListener {

            var username = binding.usernametext.text.toString()
            var password = binding.passwordtext.text.toString()


            if(username == "admin" && password == "admin" || username == registerUsernameData && password == registerPasswordData ) {
                val goToMain = Intent(
                    applicationContext,
                    MainActivity::class.java
                )

                goToMain.putExtra("username", username)

                val bundle = Bundle()
                bundle.putString("bundle_username", username)
                goToMain.putExtras(bundle)

                PreferenceUtility(applicationContext).apply {
                    saveStringPreferences("username", binding.usernametext.text.toString())
                    saveStringPreferences("password", binding.passwordtext.text.toString())
                }

                startActivity(goToMain)
                finish()
            }else{
                Snackbar.make(binding.root,
                    "Please provide admin/admin",
                    Snackbar.LENGTH_SHORT).show()
            }
        }

        PreferenceUtility(applicationContext).apply {
            binding.usernametext.setText(getStringPreferences("username" ))
            binding.passwordtext.setText(getStringPreferences("password" ))
        }

    }


}