package org.example.app.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.example.app.R
import org.example.app.ui.util.ViewExtensions.setVisible

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val emailLayout = view.findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.passwordLayout)
        val emailInput = view.findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.passwordInput)

        val errorText = view.findViewById<android.widget.TextView>(R.id.errorText)
        val loginButton = view.findViewById<MaterialButton>(R.id.loginButton)
        val goToSignUpButton = view.findViewById<MaterialButton>(R.id.goToSignUpButton)

        // Observe field errors
        viewModel.emailError.observe(viewLifecycleOwner) { msg ->
            emailLayout.error = msg
        }
        viewModel.passwordError.observe(viewLifecycleOwner) { msg ->
            passwordLayout.error = msg
        }
        viewModel.generalError.observe(viewLifecycleOwner) { msg ->
            errorText.text = msg ?: ""
            errorText.setVisible(!msg.isNullOrBlank())
        }

        viewModel.navigateToDashboard.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate == true) {
                findNavController().navigate(R.id.action_login_to_dashboard)
                viewModel.onNavigatedToDashboard()
            }
        }

        loginButton.setOnClickListener {
            viewModel.onLoginClicked(
                email = emailInput.text?.toString().orEmpty(),
                password = passwordInput.text?.toString().orEmpty()
            )
        }

        goToSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
    }
}
