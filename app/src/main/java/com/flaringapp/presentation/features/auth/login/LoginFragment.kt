package com.flaringapp.presentation.features.auth.login

import androidx.core.widget.doAfterTextChanged
import com.flaringapp.base.R
import com.flaringapp.base.databinding.FragmentLoginBinding
import com.flaringapp.presentation.base.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : ModelledFragment(R.layout.fragment_login) {

    override val model: AuthViewModel by viewModel()

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun initViews() {
        binding.emailInputEditText.doAfterTextChanged {
            model.setEmail(it.toString())
            binding.emailInputLayout.error = null
        }
        binding.passwordInputEditText.doAfterTextChanged {
            model.setPassword(it.toString())
            binding.passwordInputLayout.error = null
        }

        binding.rememberMeCheckBox.setOnCheckedChangeListener { _, checked ->
            model.setRememberMe(checked)
        }

        binding.signInButton.setOnClickListener { model.login() }
        binding.signUpTextView.setOnClickListener { model.signUp() }
    }

    override fun observeModel() {
        model.invalidEmailLiveData.observe(viewLifecycleOwner) {
            binding.emailInputLayout.error = getString(R.string.error_invalid_email)
        }

        model.invalidPasswordLiveData.observe(viewLifecycleOwner) {
            binding.passwordInputLayout.error = getString(R.string.error_invalid_password)
        }

        model.authSuccessLiveData.observe(viewLifecycleOwner) {
            //TODO login navigate to main
        }

        model.loadingLiveData.observe(viewLifecycleOwner) {
            //TODO login add progress
        }
    }
}