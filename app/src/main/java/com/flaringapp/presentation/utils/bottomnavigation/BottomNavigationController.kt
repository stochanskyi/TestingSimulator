package com.flaringapp.presentation.utils.bottomnavigation

import android.content.Intent
import android.util.SparseArray
import android.view.MenuItem
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.flaringapp.app.common.forEachIndexed
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

typealias MenuToNavigationMap = LinkedHashMap<Int, Int>

class BottomNavigationController(
    fragmentManager: FragmentManager,
    containerId: Int,
) {

    companion object {
        fun create(activity: AppCompatActivity, containerId: Int): BottomNavigationController {
            return BottomNavigationController(
                activity.supportFragmentManager,
                containerId
            ).withDeepLinks(activity.intent)
        }
    }

    private val mapping = Mapping()

    private val fragmentController =
        NavigationFragmentController(fragmentManager, containerId)

    private val selectedNavController = MutableLiveData<NavController>()

    private lateinit var lastSelectedItemTag: String
    private var isOnFirstFragment = false

    private var deepLinksIntent: Intent? = null

    fun withDeepLinks(intent: Intent) = apply {
        deepLinksIntent = intent
    }

    fun setup(navigation: BottomNavigationView, menuToNavigationMap: MenuToNavigationMap) = apply {
        mapping.clear()

        navigation.setupNavigationHosts(menuToNavigationMap)

        navigation.setupItemSelected()
        navigation.setupItemReselected()

        deepLinksIntent?.let {
            navigation.setupDeepLinks(menuToNavigationMap.keys.toList(), it)
        }

        navigation.updateOnBackStackChanged()
    }

    fun getNavigationControllerLiveData(): LiveData<NavController> {
        return selectedNavController
    }

    fun showExternalFragment(fragment: Fragment) {
        fragmentController.addExternalFragment(fragment, lastSelectedItemTag)
    }

    fun handleExternalFragmentBackPressed(): Boolean {
        return tryToCloseExternalFragment()
    }

    private fun BottomNavigationView.setupNavigationHosts(menuToNavigationMap: MenuToNavigationMap) {
        menuToNavigationMap.forEachIndexed { index, itemId, navigationId ->
            setupMenuItemWithNavigation(index, itemId, navigationId)
        }
    }

    private fun BottomNavigationView.setupMenuItemWithNavigation(
        index: Int,
        menuItemId: Int,
        navigationId: Int
    ) {
        val fragmentTag = getFragmentTag(index)

        val navHostFragment = resolveNavHostFragment(fragmentTag, navigationId)
        val graphId = navHostFragment.navController.graph.id

        mapping.link(menuItemId, graphId, fragmentTag)

        if (selectedItemId == menuItemId) {
            selectedNavController.value = navHostFragment.navController
        } else {
            fragmentController.detachNavigationFragment(navHostFragment)
        }
    }

    private fun BottomNavigationView.setupItemSelected() {
        setOnItemSelectedListener(
            BottomNavigationItemSelectedProcessor(selectedItemId)
        )
    }

    private inner class BottomNavigationItemSelectedProcessor(selectedItemId: Int) :
        NavigationBarView.OnItemSelectedListener {

        private val firstFragmentTag = mapping.firstTag

        init {
            lastSelectedItemTag = mapping.tagForItem(selectedItemId)
            isOnFirstFragment = lastSelectedItemTag == firstFragmentTag
        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            if (fragmentController.isStateSaved) return false

            val selectedItemTag = mapping.tagForItem(item.itemId)
            if (lastSelectedItemTag == selectedItemTag) return false

            val selectedFragment = fragmentController.findNavigationFragment(selectedItemTag)!!

            fragmentController.closeExternalFragment()

            fragmentController.closeSecondaryNavigationFragment()

            if (selectedItemTag != firstFragmentTag) {
                fragmentController.openSecondaryNavigationFragment(
                    selectedFragment,
                    firstFragmentTag
                )
            }

            lastSelectedItemTag = selectedItemTag
            isOnFirstFragment = lastSelectedItemTag == firstFragmentTag

            selectedNavController.value = selectedFragment.navController

            return true
        }
    }

    private fun BottomNavigationView.setupItemReselected() {
        setOnItemReselectedListener { item ->
            if (tryToCloseExternalFragment()) return@setOnItemReselectedListener

            val newlySelectedItemTag = mapping.tagForItem(item.itemId)

            val selectedFragment = fragmentController.findNavigationFragment(newlySelectedItemTag)!!
            val navController = selectedFragment.navController

            navController.popBackStack(navController.graph.startDestination, false)
        }
    }

    private fun BottomNavigationView.setupDeepLinks(
        navGraphIds: List<Int>,
        intent: Intent
    ) {
        navGraphIds.forEachIndexed { index, _ ->
            val fragmentTag = getFragmentTag(index)
            val navHostFragment = fragmentController.findNavigationFragment(fragmentTag)!!

            val itemId = mapping.itemForGraph(navHostFragment.navController.graph.id)
            if (navHostFragment.navController.handleDeepLink(intent) && selectedItemId != itemId) {
                this.selectedItemId = itemId
                return
            }
        }
    }

    private fun BottomNavigationView.updateOnBackStackChanged() {
        // When bottom navigation fragment is popped (we navigate from any except 1st to the 1st)
        // then we need to change bottom navigation selected item to the 1st one
        fragmentController.listenToBackStackChanges {
            if (!isOnFirstFragment && !fragmentController.hasNavigationOnBackStack()) {
                this.selectedItemId = mapping.firstItemId
            }

            selectedNavController.value?.let { controller ->
                if (controller.currentDestination == null) {
                    controller.navigate(controller.graph.id)
                }
            }
        }
    }

    private fun resolveNavHostFragment(
        fragmentTag: String,
        @NavigationRes navigationId: Int,
    ): NavHostFragment {
        fragmentController.findNavigationFragment(fragmentTag)?.let { return it }

        val navHostFragment = NavHostFragment.create(navigationId)
        fragmentController.addNavigationFragment(navHostFragment, fragmentTag)

        return navHostFragment
    }

    private fun getFragmentTag(index: Int) = "bottomNavigation#$index"

    private fun tryToCloseExternalFragment(): Boolean {
        if (!fragmentController.hasExternalFragment()) return false
        fragmentController.closeExternalFragment()
        selectedNavController.value = selectedNavController.value
        return true
    }

    private class Mapping {

        private val menuToGraphIdMap: MutableMap<Int, Int> = mutableMapOf()
        private val graphIdToTagMap = SparseArray<String>()

        private var mFirstGraphId: Int? = null

        val firstItemId: Int
            get() = itemForGraph(mFirstGraphId!!)

        val firstTag: String
            get() = tagForGraph(mFirstGraphId!!)

        fun link(itemId: Int, graphId: Int, tag: String) {
            menuToGraphIdMap[itemId] = graphId
            graphIdToTagMap.put(graphId, tag)
            if (mFirstGraphId == null) {
                mFirstGraphId = graphId
            }
        }

        fun clear() {
            mFirstGraphId = null
            menuToGraphIdMap.clear()
            graphIdToTagMap.clear()
        }

        fun graphForItem(itemId: Int) = menuToGraphIdMap[itemId]!!
        fun itemForGraph(graphId: Int) = menuToGraphIdMap.entries.find { it.value == graphId }!!.key

        fun tagForItem(itemId: Int) = graphIdToTagMap[menuToGraphIdMap[itemId]!!]!!

        fun tagForGraph(graphId: Int) = graphIdToTagMap[graphId]!!

    }

}