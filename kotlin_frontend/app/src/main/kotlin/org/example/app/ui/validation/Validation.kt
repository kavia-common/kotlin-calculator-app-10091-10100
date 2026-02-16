package org.example.app.ui.validation

object Validation {

    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    // PUBLIC_INTERFACE
    fun emailError(email: String): String? {
        /** Returns an error message if email is invalid; otherwise null. */
        if (email.isBlank()) return "Email is required."
        if (!EMAIL_REGEX.matches(email.trim())) return "Enter a valid email."
        return null
    }

    // PUBLIC_INTERFACE
    fun passwordError(password: String): String? {
        /** Returns an error message if password is invalid; otherwise null. */
        if (password.isBlank()) return "Password is required."
        if (password.length < 6) return "Password must be at least 6 characters."
        return null
    }

    // PUBLIC_INTERFACE
    fun confirmPasswordError(password: String, confirmPassword: String): String? {
        /** Returns an error message if confirm password is invalid; otherwise null. */
        if (confirmPassword.isBlank()) return "Please confirm your password."
        if (password != confirmPassword) return "Passwords do not match."
        return null
    }
}
