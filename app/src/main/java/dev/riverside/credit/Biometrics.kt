package dev.riverside.credit

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.biometric.BiometricPrompt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.hardware.biometrics.BiometricManager.Authenticators
import androidx.biometric.BiometricManager
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dev.riverside.credit.databinding.AuthBiometricBinding
import java.util.concurrent.Executor
import kotlin.system.exitProcess

class Biometrics : Fragment() {

    private var _binding: AuthBiometricBinding? = null
    private val binding get() = _binding!!

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

        // Create the callback
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // TODO: Handle error
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                val navController = view?.findNavController()
                navController?.navigate(R.id.action_biometrics_to_homepage)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // TODO: Handle failure
                exitProcess(77)
            }
        }

        // Initialize BiometricPrompt instance
        val biometricPrompt = BiometricPrompt(this, executor, callback)

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verify that it's you")
            .setDescription("For your security, you need to verify that it's you before using Smart Tap")
            .setNegativeButtonText(" ")
            .setConfirmationRequired(true)
            .build()

        // Start authentication
        biometricPrompt.authenticate(promptInfo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

///**
// * A simple [Fragment] subclass as the default destination in the navigation.
// */
//class Biometrics : Fragment() {
//
//    private var _binding: AuthBiometricBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = AuthBiometricBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        binding.buttonFirst.setOnClickListener {
////            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
////        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}