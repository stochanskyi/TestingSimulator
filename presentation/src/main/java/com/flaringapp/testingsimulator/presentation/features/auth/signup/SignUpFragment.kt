package com.flaringapp.testingsimulator.presentation.features.auth.signup

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.flaringapp.testingsimulator.core.presentation.utils.doOnDoneClicked
import com.flaringapp.testingsimulator.core.presentation.utils.hideKeyboardAndClearCurrentFocus
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.observeOnce
import com.flaringapp.testingsimulator.presentation.features.auth.launcher.ScreenLauncher
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentSignUpBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : ModelledFragment(R.layout.fragment_sign_up) {

    override val model: SignUpVewModel by viewModel()

    private val binding: FragmentSignUpBinding by viewBinding { FragmentSignUpBinding.bind(it) }

    private val screenLauncher: ScreenLauncher by inject()

    override fun initViews() = with(binding) {
        emailInputEditText.doAfterTextChanged {
            model.setEmail(it.toString())
            emailInputLayout.error = null
        }
        firstNameInputEditText.doAfterTextChanged {
            model.setFirstName(it.toString())
            firstNameInputLayout.error = null
        }
        lastNameInputEditText.doAfterTextChanged {
            model.setLastName(it.toString())
            lastNameInputLayout.error = null
        }
        studyingInputEditText.doAfterTextChanged {
            model.setStudying(it.toString())
            studyingInputLayout.error = null
        }
        workPlaceInputEditText.doAfterTextChanged {
            model.setWorkPlace(it.toString())
            workPlaceInputLayout.error = null
        }
        roleInputEditText.doAfterTextChanged {
            model.setRole(it.toString())
            roleInputLayout.error = null
        }
        passwordInputEditText.doAfterTextChanged {
            model.setPassword(it.toString())
            passwordInputLayout.error = null
        }
        confirmPasswordInputEditText.doAfterTextChanged {
            model.setConfirmPassword(it.toString())
            confirmPasswordInputLayout.error = null
        }
        confirmPasswordInputEditText.doOnDoneClicked {
            signUpButton.performClick()
        }

        signUpButton.setOnClickListener {
            hideKeyboardAndClearCurrentFocus()
            model.signUp()
        }
    }

    override fun observeModel() = with(model) {
        isStudyingEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.studyingInputLayout.isVisible = isEnabled
        }
        isWorkPlaceEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.workPlaceInputLayout.isVisible = isEnabled
        }
        isRoleEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.roleInputLayout.isVisible = isEnabled
        }

        emailLiveData.observeOnce(viewLifecycleOwner) { email ->
            binding.emailInputEditText.setText(email)
        }
        firstNameLiveData.observeOnce(viewLifecycleOwner) { firstName ->
            binding.firstNameInputEditText.setText(firstName)
        }
        lastNameLiveData.observeOnce(viewLifecycleOwner) { lastName ->
            binding.lastNameInputEditText.setText(lastName)
        }
        studyingLiveData.observeOnce(viewLifecycleOwner) { studying ->
            binding.studyingInputEditText.setText(studying)
        }
        workPlaceLiveData.observeOnce(viewLifecycleOwner) { workPlace ->
            binding.workPlaceInputEditText.setText(workPlace)
        }
        roleLiveData.observeOnce(viewLifecycleOwner) { role ->
            binding.roleInputEditText.setText(role)
        }
        passwordLiveData.observeOnce(viewLifecycleOwner) { password ->
            binding.passwordInputEditText.setText(password)
        }
        confirmPasswordLiveData.observeOnce(viewLifecycleOwner) { confirmPassword ->
            binding.confirmPasswordInputEditText.setText(confirmPassword)
        }

        invalidEmailLiveData.observe(viewLifecycleOwner) {
            binding.emailInputLayout.error = getString(R.string.error_invalid_email)
        }
        invalidFirstNameLiveData.observe(viewLifecycleOwner) {
            binding.firstNameInputLayout.error = getString(R.string.error_invalid_first_name)
        }
        invalidLastNameLiveData.observe(viewLifecycleOwner) {
            binding.lastNameInputLayout.error = getString(R.string.error_invalid_last_name)
        }
        invalidPasswordLiveData.observe(viewLifecycleOwner) {
            binding.passwordInputLayout.error = getString(R.string.error_invalid_password)
        }
        passwordsNotEqualLiveData.observe(viewLifecycleOwner) {
            binding.confirmPasswordInputLayout.error = getString(R.string.error_passwords_not_equal)
        }

        model.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isInvisible = !isLoading
            binding.signUpButton.isEnabled = !isLoading
        }

        model.authSuccessLiveData.observe(viewLifecycleOwner) {
            activity?.finish()
            screenLauncher.launchMainScreen(context ?: return@observe)
        }
    }
}