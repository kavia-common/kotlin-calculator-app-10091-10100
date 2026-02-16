package org.example.app.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class DashboardViewModel : ViewModel() {

    private val _operandAError = MutableLiveData<String?>(null)
    val operandAError: LiveData<String?> = _operandAError

    private val _operandBError = MutableLiveData<String?>(null)
    val operandBError: LiveData<String?> = _operandBError

    private val _generalError = MutableLiveData<String?>(null)
    val generalError: LiveData<String?> = _generalError

    private val _resultText = MutableLiveData<String>("—")
    val resultText: LiveData<String> = _resultText

    private val df = DecimalFormat("#.##########")

    // PUBLIC_INTERFACE
    fun onOperation(op: Operation, rawA: String, rawB: String) {
        /** Perform calculator operation with validation and update result/error LiveData. */
        _generalError.value = null

        val a = rawA.toDoubleOrNull()
        val b = rawB.toDoubleOrNull()

        _operandAError.value = if (a == null) "Enter a valid number." else null
        _operandBError.value = if (b == null) "Enter a valid number." else null

        if (a == null || b == null) {
            _generalError.value = "Please fix the highlighted fields."
            return
        }

        if (op == Operation.DIVIDE && b == 0.0) {
            _generalError.value = "Cannot divide by zero."
            _resultText.value = "—"
            return
        }

        val result = when (op) {
            Operation.ADD -> a + b
            Operation.SUBTRACT -> a - b
            Operation.MULTIPLY -> a * b
            Operation.DIVIDE -> a / b
        }

        _resultText.value = df.format(result)
    }

    // PUBLIC_INTERFACE
    fun onClear() {
        /** Reset errors and result. */
        _operandAError.value = null
        _operandBError.value = null
        _generalError.value = null
        _resultText.value = "—"
    }
}
