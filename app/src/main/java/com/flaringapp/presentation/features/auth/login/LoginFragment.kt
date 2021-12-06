package com.flaringapp.presentation.features.auth.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.flaringapp.base.R
import com.flaringapp.base.databinding.FragmentLoginBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: AuthViewModel by viewModel()

    private val binding by viewBinding { FragmentLoginBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.emailInputEditText.doAfterTextChanged {
            viewModel.setEmail(it.toString())
            binding.emailInputLayout.error = null
        }
        binding.passwordInputEditText.doAfterTextChanged {
            viewModel.setPassword(it.toString())
            binding.passwordInputLayout.error = null
        }

        binding.rememberMeCheckBox.setOnCheckedChangeListener { _, checked ->
            viewModel.setRememberMe(checked)
        }

        binding.signInButton.setOnClickListener { viewModel.login() }
        binding.signUpTextView.setOnClickListener { viewModel.signUp() }
    }

    private fun initObservers() {
        viewModel.invalidEmailLiveData.observe(viewLifecycleOwner) {
            binding.emailInputLayout.error = getString(R.string.error_invalid_email)
        }

        viewModel.invalidPasswordLiveData.observe(viewLifecycleOwner) {
            binding.passwordInputLayout.error = getString(R.string.error_invalid_password)
        }

        viewModel.authSuccessLiveData.observe(viewLifecycleOwner) {
            //TODO navigate to main
        }

        viewModel.loginLoadingLiveData.observe(viewLifecycleOwner) {
            //TODO add progress
        }
    }
}