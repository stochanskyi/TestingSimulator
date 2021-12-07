package com.flaringapp.testingsimulator.presentation.features.auth.login

import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.observeOnce
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentLoginBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : ModelledFragment(R.layout.fragment_login) {

    override val model: LoginViewModel by viewModel()

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun initViews() = with(binding) {
        emailInputEditText.doAfterTextChanged {
            model.setEmail(it.toString())
            binding.emailInputLayout.error = null
        }
        passwordInputEditText.doAfterTextChanged {
            model.setPassword(it.toString())
            binding.passwordInputLayout.error = null
        }
        rememberMeCheckBox.setOnCheckedChangeListener { _, checked ->
            model.setRememberMe(checked)
        }

        signInButton.setOnClickListener { model.login() }
        signUpTextView.setOnClickListener { model.signUp() }
    }

    override fun observeModel() = with(model) {
        signUpEnabledLiveData.observe(viewLifecycleOwner) {
            binding.orTextView.isVisible = it
            binding.signUpTextView.isVisible = it
        }

        emailLiveData.observeOnce(viewLifecycleOwner) { email ->
            binding.emailInputEditText.setText(email)
        }

        passwordLiveData.observeOnce(viewLifecycleOwner) { password ->
            binding.passwordInputEditText.setText(password)
        }

        rememberMeLiveData.observeOnce(viewLifecycleOwner) { rememberMe ->
            binding.rememberMeCheckBox.isChecked = rememberMe
        }

        invalidEmailLiveData.observe(viewLifecycleOwner) {
            binding.emailInputLayout.error = getString(R.string.error_invalid_email)
        }

        invalidPasswordLiveData.observe(viewLifecycleOwner) {
            binding.passwordInputLayout.error = getString(R.string.error_invalid_password)
        }

        loadingLiveData.observe(viewLifecycleOwner) {
            //TODO login add progress
        }

        openSignUpLiveData.observe(viewLifecycleOwner) {
            openSignUp()
        }

        authSuccessLiveData.observe(viewLifecycleOwner) {
            //TODO login navigate to main
        }
    }

    private fun openSignUp() {
        findNavController().navigate(
            LoginFragmentDirections.actionFragmentLoginToFragmentSignUp()
        )
    }
}