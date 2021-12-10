package com.flaringapp.testingsimulator.presentation.features.auth.login

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.flaringapp.testingsimulator.core.presentation.utils.doOnDoneClicked
import com.flaringapp.testingsimulator.core.presentation.utils.hideKeyboardAndClearCurrentFocus
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.observeOnce
import com.flaringapp.testingsimulator.presentation.features.auth.launcher.ScreenLauncher
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentLoginBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : ModelledFragment(R.layout.fragment_login) {

    override val model: LoginViewModel by viewModel()

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val screenLauncher: ScreenLauncher by inject()

    override fun initViews() = with(binding) {
        emailInputEditText.doAfterTextChanged {
            model.setEmail(it.toString())
            binding.emailInputLayout.error = null
        }
        passwordInputEditText.doAfterTextChanged {
            model.setPassword(it.toString())
            binding.passwordInputLayout.error = null
        }
        passwordInputEditText.doOnDoneClicked {
            signInButton.performClick()
        }

        rememberMeCheckBox.setOnCheckedChangeListener { _, checked ->
            model.setRememberMe(checked)
        }

        signInButton.setOnClickListener {
            hideKeyboardAndClearCurrentFocus()
            model.login()
        }

        signUpTextView.setOnClickListener {
            model.signUp()
        }
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

        passwordEmptyLiveData.observe(viewLifecycleOwner) {
            binding.passwordInputLayout.error = getString(R.string.error_password_empty)
        }

        loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isInvisible = !isLoading
            binding.signInButton.isEnabled = !isLoading
        }

        openSignUpLiveData.observe(viewLifecycleOwner) {
            openSignUp()
        }

        model.authSuccessLiveData.observe(viewLifecycleOwner) {
            activity?.finish()
            screenLauncher.launchMainScreen(context ?: return@observe)
        }
    }

    private fun openSignUp() {
        findNavController().navigate(
            LoginFragmentDirections.actionFragmentLoginToFragmentSignUp()
        )
    }
}