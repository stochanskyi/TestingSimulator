package com.flaringapp.testingsimulator.presentation.utils.bottomnavigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment

class NavigationFragmentController(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {

    companion object {
        private const val NAVIGATION_FRAGMENT_TAG = "navigation_fragment"
        private const val EXTERNAL_FRAGMENT_TAG = "external_fragment"
    }

    private val bottomNavController = BottomNavigationFragmentController()
    private val externalController = ExternalFragmentController()

    val isStateSaved: Boolean
        get() = fragmentManager.isStateSaved

    fun findNavigationFragment(tag: String): NavHostFragment? {
        return fragmentManager.findFragmentByTag(tag) as? NavHostFragment
    }

    fun addNavigationFragment(fragment: Fragment, tag: String) {
        bottomNavController.addNavigationFragment(fragment, tag)
    }

    fun detachNavigationFragment(fragment: Fragment) {
        bottomNavController.detachNavigationFragment(fragment)
    }

    fun openSecondaryNavigationFragment(fragment: Fragment, primaryTag: String) {
        bottomNavController.openSecondaryNavigationFragment(fragment, primaryTag)
    }

    fun closeSecondaryNavigationFragment() {
        bottomNavController.closeSecondaryNavigationFragment()
    }

    fun hasNavigationOnBackStack(): Boolean {
        val backStackCount = fragmentManager.backStackEntryCount
        for (index in 0 until backStackCount) {
            if (fragmentManager.getBackStackEntryAt(index).name == NAVIGATION_FRAGMENT_TAG) {
                return true
            }
        }
        return false
    }

    fun hasExternalFragment() = externalController.isFragmentShown

    fun addExternalFragment(fragment: Fragment, currentItemTag: String) {
        externalController.showExternalFragment(fragment, currentItemTag)
    }

    fun closeExternalFragment() {
        externalController.closeExternalFragment()
    }

    fun listenToBackStackChanges(listener: FragmentManager.OnBackStackChangedListener) {
        fragmentManager.addOnBackStackChangedListener(listener)
    }

    private inner class BottomNavigationFragmentController {

        fun addNavigationFragment(fragment: Fragment, tag: String) {
            fragmentManager.beginTransaction()
                .add(containerId, fragment, tag)
                .commitNow()
        }

        fun detachNavigationFragment(fragment: Fragment) {
            fragmentManager.beginTransaction()
                .detach(fragment)
                .commitNow()
        }

        fun openSecondaryNavigationFragment(fragment: Fragment, primaryTag: String) {
            fragmentManager.beginTransaction()
                .attach(fragment)
                .setPrimaryNavigationFragment(fragment)
                .detach(fragmentManager.findFragmentByTag(primaryTag)!!)
                .addToBackStack(NAVIGATION_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }

        fun closeSecondaryNavigationFragment() {
            fragmentManager.popBackStackInclusive(NAVIGATION_FRAGMENT_TAG)
        }
    }

    private inner class ExternalFragmentController {

        val isFragmentShown: Boolean
            get() = hiddenItemTag != null

        private var hiddenItemTag: String? = null

        fun showExternalFragment(fragment: Fragment, currentItemTag: String) {
            if (fragmentManager.isStateSaved) return

            fragmentManager.beginTransaction()
                .hideCurrentFragment(currentItemTag)
                .add(containerId, fragment, EXTERNAL_FRAGMENT_TAG)
                .setPrimaryNavigationFragment(fragment)
                .addToBackStack(EXTERNAL_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()

            hiddenItemTag = currentItemTag
        }

        fun closeExternalFragment() {
            if (fragmentManager.isStateSaved) return

            fragmentManager.popBackStackInclusive(EXTERNAL_FRAGMENT_TAG)

            showHiddenFragment()
        }

        private fun FragmentTransaction.hideCurrentFragment(
            fragmentTag: String
        ): FragmentTransaction {
            if (hiddenItemTag != null) return this

            val fragment = fragmentManager.findFragmentByTag(fragmentTag)!!
            hide(fragment)

            hiddenItemTag = fragmentTag

            return this
        }

        private fun showHiddenFragment() {
            val tag = hiddenItemTag ?: return

            val currentFragment = fragmentManager.findFragmentByTag(tag)!!
            fragmentManager.beginTransaction()
                .show(currentFragment)
                .commit()

            hiddenItemTag = null
        }
    }

    private fun FragmentManager.popBackStackInclusive(tag: String) {
        popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}