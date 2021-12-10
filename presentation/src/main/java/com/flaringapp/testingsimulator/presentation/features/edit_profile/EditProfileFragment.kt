package com.flaringapp.testingsimulator.presentation.features.edit_profile

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
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

        saveButton.setOnClickListener {
            hideKeyboardAndClearCurrentFocus()
            model.save()
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

        invalidFirstNameLiveData.observe(viewLifecycleOwner) {
            binding.firstNameInputLayout.error = getString(R.string.error_invalid_first_name)
        }
        invalidLastNameLiveData.observe(viewLifecycleOwner) {
            binding.lastNameInputLayout.error = getString(R.string.error_invalid_last_name)
        }

        observeFields()

        updateFieldsLiveData.observe(viewLifecycleOwner) {
             observeFields()
        }

        model.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isInvisible = !isLoading
            binding.saveButton.isEnabled = !isLoading
        }

        model.editSuccessLiveData.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun observeFields() = with(model) {
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
    }

}