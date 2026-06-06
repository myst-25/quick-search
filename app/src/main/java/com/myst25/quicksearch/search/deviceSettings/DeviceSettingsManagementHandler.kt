package com.myst25.quicksearch.search.deviceSettings

import com.myst25.quicksearch.search.core.GenericManagementHandler
import com.myst25.quicksearch.search.core.ManagementHandler
import com.myst25.quicksearch.search.core.SearchUiState
import com.myst25.quicksearch.search.core.SettingsManagementConfig
import com.myst25.quicksearch.search.data.UserAppPreferences
import kotlinx.coroutines.CoroutineScope

/**
 * Handles settings management operations like pinning, excluding, and nicknames.
 */
class DeviceSettingsManagementHandler(
    private val userPreferences: UserAppPreferences,
    private val scope: CoroutineScope,
    private val onStateChanged: () -> Unit,
    private val onUiStateUpdate: ((SearchUiState) -> SearchUiState) -> Unit,
) : ManagementHandler<DeviceSetting> by GenericManagementHandler(
        SettingsManagementConfig(),
        userPreferences,
        scope,
        onStateChanged,
        onUiStateUpdate,
    ) {
    // Convenience methods that delegate to the interface
    fun pinSetting(setting: DeviceSetting) = pinItem(setting)

    fun unpinSetting(setting: DeviceSetting) = unpinItem(setting)

    fun excludeSetting(setting: DeviceSetting) = excludeItem(setting)

    fun removeExcludedSetting(setting: DeviceSetting) = removeExcludedItem(setting)

    fun setSettingNickname(
        setting: DeviceSetting,
        nickname: String?,
    ) = setItemNickname(setting, nickname)

    fun getSettingNickname(id: String): String? = getItemNickname(DeviceSetting(id, "", "", emptyList(), ""))

    fun clearAllExcludedSettings() = clearAllExcludedItems()
}
