package org.example.app.ui.signup

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

class SignUpFragment : Fragment(R.layout.fragment_signup) {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val emailLayout = view.findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.passwordLayout)
        val confirmPasswordLayout = view.findViewById<TextInputLayout>(R.id.confirmPasswordLayout)

        val emailInput = view.findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.passwordInput)
        val confirmPasswordInput = view.findViewById<TextInputEditText>(R.id.confirmPasswordInput)

        val errorText = view.findViewById<android.widget.TextView>(R.id.errorText)
        val signUpButton = view.findViewById<MaterialButton>(R.id.signUpButton)
        val backToLoginButton = view.findViewById<MaterialButton>(R.id.backToLoginButton)

        viewModel.emailError.observe(viewLifecycleOwner) { msg ->
            emailLayout.error = msg
        }
        viewModel.passwordError.observe(viewLifecycleOwner) { msg ->
            passwordLayout.error = msg
        }
        viewModel.confirmPasswordError.observe(viewLifecycleOwner) { msg ->
            confirmPasswordLayout.error = msg
        }
        viewModel.generalError.observe(viewLifecycleOwner) { msg ->
            errorText.text = msg ?: ""
            errorText.setVisible(!msg.isNullOrBlank())
        }

        viewModel.navigateToDashboard.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate == true) {
                findNavController().navigate(R.id.action_signup_to_dashboard)
                viewModel.onNavigatedToDashboard()
            }
        }

        signUpButton.setOnClickListener {
            viewModel.onSignUpClicked(
                email = emailInput.text?.toString().orEmpty(),
                password = passwordInput.text?.toString().orEmpty(),
                confirmPassword = confirmPasswordInput.text?.toString().orEmpty()
            )
        }

        backToLoginButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
