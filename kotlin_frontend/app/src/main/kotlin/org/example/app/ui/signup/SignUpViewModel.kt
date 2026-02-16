package org.example.app.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.example.app.ui.validation.Validation

class SignUpViewModel : ViewModel() {

    private val _emailError = MutableLiveData<String?>(null)
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>(null)
    val passwordError: LiveData<String?> = _passwordError

    private val _confirmPasswordError = MutableLiveData<String?>(null)
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

    private val _generalError = MutableLiveData<String?>(null)
    val generalError: LiveData<String?> = _generalError

    private val _navigateToDashboard = MutableLiveData<Boolean>(false)
    val navigateToDashboard: LiveData<Boolean> = _navigateToDashboard

    // PUBLIC_INTERFACE
    fun onSignUpClicked(email: String, password: String, confirmPassword: String) {
        /** Validate fields and trigger navigation on success. */
        _generalError.value = null

        val emailMsg = Validation.emailError(email)
        val pwdMsg = Validation.passwordError(password)
        val confirmMsg = Validation.confirmPasswordError(password, confirmPassword)

        _emailError.value = emailMsg
        _passwordError.value = pwdMsg
        _confirmPasswordError.value = confirmMsg

        val hasError = !emailMsg.isNullOrBlank() || !pwdMsg.isNullOrBlank() || !confirmMsg.isNullOrBlank()
        if (hasError) {
            _generalError.value = "Please fix the highlighted fields."
            return
        }

        // No backend/signup in scope: treat valid input as successful sign-up.
        _navigateToDashboard.value = true
    }

    // PUBLIC_INTERFACE
    fun onNavigatedToDashboard() {
        /** Consume one-time navigation event. */
        _navigateToDashboard.value = false
    }
}
