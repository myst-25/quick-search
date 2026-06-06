package com.myst25.quicksearch.search.appShortcuts

import com.myst25.quicksearch.search.core.AppShortcutManagementConfig
import com.myst25.quicksearch.search.core.GenericManagementHandler
import com.myst25.quicksearch.search.core.ManagementHandler
import com.myst25.quicksearch.search.core.SearchUiState
import com.myst25.quicksearch.search.data.AppShortcutRepository.StaticShortcut
import com.myst25.quicksearch.search.data.UserAppPreferences
import kotlinx.coroutines.CoroutineScope

/**
 * Handles app shortcut management operations like pinning and excluding.
 */
class AppShortcutManagementHandler(
    private val userPreferences: UserAppPreferences,
    private val scope: CoroutineScope,
    private val onStateChanged: () -> Unit,
    private val onUiStateUpdate: ((SearchUiState) -> SearchUiState) -> Unit,
) : ManagementHandler<StaticShortcut> by GenericManagementHandler(
        AppShortcutManagementConfig(),
        userPreferences,
        scope,
        onStateChanged,
        onUiStateUpdate,
    ) {
    fun pinShortcut(shortcut: StaticShortcut) = pinItem(shortcut)

    fun unpinShortcut(shortcut: StaticShortcut) = unpinItem(shortcut)

    fun excludeShortcut(shortcut: StaticShortcut) = excludeItem(shortcut)

    fun removeExcludedShortcut(shortcut: StaticShortcut) = removeExcludedItem(shortcut)

    fun setShortcutNickname(
        shortcut: StaticShortcut,
        nickname: String?,
    ) = setItemNickname(shortcut, nickname)

    fun getShortcutNickname(shortcutId: String): String? = userPreferences.getAppShortcutNickname(shortcutId)

    fun clearAllExcludedShortcuts() = clearAllExcludedItems()
}
