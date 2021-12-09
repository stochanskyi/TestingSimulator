package com.flaringapp.testingsimulator.presentation.features.edit_profile

import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.flaringapp.testingsimulator.core.presentation.utils.hideKeyboardAndClearCurrentFocus
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.observeOnce
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentEditProfileBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment : ModelledFragment(R.layout.fragment_edit_profile) {

    override val model: EditProfileViewModel by viewModel()

    private val binding: FragmentEditProfileBinding by viewBinding(FragmentEditProfileBinding::bind)

    override fun initViews() = with(binding) {
        firstNameInputEditText.doAfterTextChanged {
            model.setFirstName(it.toString())
            firstNameInputLayout.error = null
        }
        lastNameInputEditText.doAfterTextChanged {
            model.setLastName(it.toString())
            lastNameInputLayout.error = null
        }
        studyingAtInputEditText.doAfterTextChanged {
            model.setStudyingAt(it.toString())
            studyingAtInputLayout.error = null
        }
        workPlaceInputEditText.doAfterTextChanged {
            model.setWorkPlace(it.toString())
            workPlaceInputLayout.error = null
        }
        roleInputEditText.doAfterTextChanged {
            model.setRole(it.toString())
            roleInputLayout.error = null
        }

        saveButton.setOnClickListener {
            hideKeyboardAndClearCurrentFocus()
            model.save()
        }
    }

    override fun observeModel() = with(model) {
        isStudyingAtEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.studyingAtInputLayout.isVisible = isEnabled
        }
        isWorkPlaceEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.workPlaceInputLayout.isVisible = isEnabled
        }
        isRoleEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.roleInputLayout.isVisible = isEnabled
        }

        firstNameLiveData.observeOnce(viewLifecycleOwner) { firstName ->
            binding.firstNameInputEditText.setText(firstName)
        }
        lastNameLiveData.observeOnce(viewLifecycleOwner) { lastName ->
            binding.lastNameInputEditText.setText(lastName)
        }
        studyingAtLiveData.observeOnce(viewLifecycleOwner) { studyingAt ->
            binding.studyingAtInputEditText.setText(studyingAt)
        }
        workPlaceLiveData.observeOnce(viewLifecycleOwner) { workPlace ->
            binding.workPlaceInputEditText.setText(workPlace)
        }
        roleLiveData.observeOnce(viewLifecycleOwner) { role ->
            binding.roleInputEditText.setText(role)
        }

        invalidFirstNameLiveData.observe(viewLifecycleOwner) {
            binding.firstNameInputLayout.error = getString(R.string.error_invalid_first_name)
        }
        invalidLastNameLiveData.observe(viewLifecycleOwner) {
            binding.lastNameInputLayout.error = getString(R.string.error_invalid_last_name)
        }

        model.loadingLiveData.observe(viewLifecycleOwner) {
            //TODO show progress
        }

        model.editSuccessLiveData.observe(viewLifecycleOwner) {
            //TODO navigate
        }
    }

}