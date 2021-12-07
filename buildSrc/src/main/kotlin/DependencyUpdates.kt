@file:Suppress("MemberVisibilityCanBePrivate")

import java.util.*

object DependencyUpdates {

    fun isValidCandidate(currentVersion: String, candidateVersion: String): Boolean {
        return isNotStable(currentVersion) || isStable(candidateVersion)
    }

    fun isNotStable(version: String): Boolean = !isStable(version)

    fun isStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.toUpperCase(Locale.ROOT).contains(it)
        }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        return stableKeyword || regex.matches(version)
    }

}