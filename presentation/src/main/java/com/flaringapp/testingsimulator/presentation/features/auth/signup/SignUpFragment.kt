package com.flaringapp.testingsimulator.presentation.features.auth.signup

import androidx.core.widget.doAfterTextChanged
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.observeOnce
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentSignUpBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : ModelledFragment(R.layout.fragment_sign_up) {

    override val model: SignUpVewModel by viewModel()

    private val binding: FragmentSignUpBinding by viewBinding { FragmentSignUpBinding.bind(it) }

    override fun initViews() {
        binding.emailInputEditText.doAfterTextChanged {
            model.setEmail(it.toString())
            binding.emailInputLayout.error = null
        }
        binding.firstNameInputEditText.doAfterTextChanged {
            model.setFirstName(it.toString())
            binding.firstNameInputLayout.error = null
        }
        binding.lastNameInputEditText.doAfterTextChanged {
            model.setLastName(it.toString())
            binding.lastNameInputLayout.error = null
        }
        binding.studyingAtInputEditText.doAfterTextChanged {
            model.setStudyingAt(it.toString())
            binding.studyingAtInputLayout.error = null
        }
        binding.workPlaceInputEditText.doAfterTextChanged {
            model.setWorkPlace(it.toString())
            binding.workPlaceInputLayout.error = null
        }
        binding.roleInputEditText.doAfterTextChanged {
            model.setRole(it.toString())
            binding.roleInputLayout.error = null
        }
        binding.passwordInputEditText.doAfterTextChanged {
            model.setPassword(it.toString())
            binding.passwordInputLayout.error = null
        }
        binding.confirmPasswordInputEditText.doAfterTextChanged {
            model.setConfirmPassword(it.toString())
            binding.confirmPasswordInputLayout.error = null
        }

        binding.signUpButton.setOnClickListener { model.signUp() }
    }

    override fun observeModel() {
        model.emailLiveData.observeOnce(viewLifecycleOwner) {
            binding.emailInputEditText.setText(it)
        }
        model.firstNameLiveData.observeOnce(viewLifecycleOwner) {
            binding.firstNameInputEditText.setText(it)
        }
        model.lastNameLiveData.observeOnce(viewLifecycleOwner) {
            binding.lastNameInputEditText.setText(it)
        }
        model.studyingAtLiveData.observeOnce(viewLifecycleOwner) {
            binding.studyingAtInputEditText.setText(it)
        }
        model.workPlaceLiveData.observeOnce(viewLifecycleOwner) {
            binding.workPlaceInputEditText.setText(it)
        }
        model.roleLiveData.observeOnce(viewLifecycleOwner) {
            binding.roleInputEditText.setText(it)
        }
        model.passwordLiveData.observeOnce(viewLifecycleOwner) {
            binding.passwordInputEditText.setText(it)
        }
        model.confirmPasswordLiveData.observeOnce(viewLifecycleOwner) {
            binding.confirmPasswordInputEditText.setText(it)
        }

        model.invalidEmailLiveData.observe(viewLifecycleOwner) {
            binding.emailInputLayout.error = getString(R.string.error_invalid_email)
        }
        model.invalidFirstNameLiveData.observe(viewLifecycleOwner) {
            binding.firstNameInputLayout.error = getString(R.string.error_invalid_first_name)
        }
        model.invalidLastNameLiveData.observe(viewLifecycleOwner) {
            binding.lastNameInputLayout.error = getString(R.string.error_invalid_last_name)
        }
        model.invalidPasswordLiveData.observe(viewLifecycleOwner) {
            binding.passwordInputLayout.error = getString(R.string.error_invalid_password)
        }
        model.passwordsNotEqualLiveData.observe(viewLifecycleOwner) {
            binding.confirmPasswordInputLayout.error = getString(R.string.error_passwords_not_equal)
        }
    }
}