package dev.riverside.credit

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.biometric.BiometricPrompt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import dev.riverside.credit.databinding.AuthBiometricBinding
import dev.riverside.credit.databinding.ActivityMainBinding
import dev.riverside.credit.Main
import java.util.concurrent.Executor
import android.app.Activity

class Biometrics : Fragment() {

    private var _binding: AuthBiometricBinding? = null
    private val binding get() = _binding!!

    private var biometricPrompt: BiometricPrompt? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthBiometricBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get an instance of the main executor
        val executor: Executor = ContextCompat.getMainExecutor(requireContext())

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verify that it's you")
            .setSubtitle("For your security, you need to verify that it's you before using Smart Tap")
            .setNegativeButtonText(" ")
            .setConfirmationRequired(true)
            .build()

        // Create the callback
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                (activity as Activity).finish()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()

            }
        }
        // Initialize BiometricPrompt instance
        val biometricPrompt = BiometricPrompt(this, executor, callback)

        // Start authentication
        biometricPrompt.authenticate(promptInfo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}