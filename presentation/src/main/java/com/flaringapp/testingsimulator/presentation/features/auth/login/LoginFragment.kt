package com.flaringapp.testingsimulator.presentation.features.auth.login

import androidx.core.widget.doAfterTextChanged
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.observeOnce
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentLoginBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : ModelledFragment(R.layout.fragment_login) {

    override val model: LoginViewModel by viewModel()

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
        model.emailLiveData.observeOnce(viewLifecycleOwner) { email ->
            binding.emailInputEditText.setText(email)
        }

        model.passwordLiveData.observeOnce(viewLifecycleOwner) { password ->
            binding.passwordInputEditText.setText(password)
        }

        model.rememberMeLiveData.observeOnce(viewLifecycleOwner) { rememberMe ->
            binding.rememberMeCheckBox.isChecked = rememberMe
        }

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