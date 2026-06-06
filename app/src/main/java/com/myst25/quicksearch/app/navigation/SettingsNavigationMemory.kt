package com.myst25.quicksearch.app.navigation

import com.myst25.quicksearch.settings.settingsDetailScreen.SettingsDetailType

object SettingsNavigationMemory {
    private var lastOpenedSettingsDetailType: SettingsDetailType? = null

    fun rememberSettingsDetail(detailType: SettingsDetailType) {
        lastOpenedSettingsDetailType = detailType
    }

    fun clear() {
        lastOpenedSettingsDetailType = null
    }

    fun getLastOpenedSettingsDetail(): SettingsDetailType? = lastOpenedSettingsDetailType
}
