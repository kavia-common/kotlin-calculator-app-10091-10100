package org.example.app.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.example.app.R
import org.example.app.ui.util.ViewExtensions.setVisible

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val operandALayout = view.findViewById<TextInputLayout>(R.id.operandALayout)
        val operandBLayout = view.findViewById<TextInputLayout>(R.id.operandBLayout)
        val operandAInput = view.findViewById<TextInputEditText>(R.id.operandAInput)
        val operandBInput = view.findViewById<TextInputEditText>(R.id.operandBInput)

        val resultText = view.findViewById<android.widget.TextView>(R.id.resultText)
        val errorText = view.findViewById<android.widget.TextView>(R.id.errorText)

        val addButton = view.findViewById<MaterialButton>(R.id.addButton)
        val subButton = view.findViewById<MaterialButton>(R.id.subButton)
        val mulButton = view.findViewById<MaterialButton>(R.id.mulButton)
        val divButton = view.findViewById<MaterialButton>(R.id.divButton)
        val clearButton = view.findViewById<MaterialButton>(R.id.clearButton)

        viewModel.operandAError.observe(viewLifecycleOwner) { msg ->
            operandALayout.error = msg
        }
        viewModel.operandBError.observe(viewLifecycleOwner) { msg ->
            operandBLayout.error = msg
        }
        viewModel.resultText.observe(viewLifecycleOwner) { text ->
            resultText.text = text
        }
        viewModel.generalError.observe(viewLifecycleOwner) { msg ->
            errorText.text = msg ?: ""
            errorText.setVisible(!msg.isNullOrBlank())
        }

        fun currentA(): String = operandAInput.text?.toString().orEmpty()
        fun currentB(): String = operandBInput.text?.toString().orEmpty()

        addButton.setOnClickListener { viewModel.onOperation(Operation.ADD, currentA(), currentB()) }
        subButton.setOnClickListener { viewModel.onOperation(Operation.SUBTRACT, currentA(), currentB()) }
        mulButton.setOnClickListener { viewModel.onOperation(Operation.MULTIPLY, currentA(), currentB()) }
        divButton.setOnClickListener { viewModel.onOperation(Operation.DIVIDE, currentA(), currentB()) }

        clearButton.setOnClickListener {
            operandAInput.setText("")
            operandBInput.setText("")
            viewModel.onClear()
        }
    }
}
