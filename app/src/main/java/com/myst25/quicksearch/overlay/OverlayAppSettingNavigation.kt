package com.myst25.quicksearch.overlay

import android.content.Context
import com.myst25.quicksearch.app.navigation.AppSettingsDestinationHandlers
import com.myst25.quicksearch.app.navigation.handleAppSettingsDestination
import com.myst25.quicksearch.app.navigation.launchDevelopmentPage
import com.myst25.quicksearch.app.navigation.launchRateQuickSearch
import com.myst25.quicksearch.app.navigation.openDefaultAssistantSettings
import com.myst25.quicksearch.app.navigation.openDefaultLauncherSettings
import com.myst25.quicksearch.search.appSettings.AppSettingsDestination
import com.myst25.quicksearch.search.core.SearchViewModel
import com.myst25.quicksearch.settings.settingsDetailScreen.SettingsDetailType
import com.myst25.quicksearch.shared.util.FeedbackUtils

internal fun handleOverlayAppSettingDestination(
    context: Context,
    destination: AppSettingsDestination,
    viewModel: SearchViewModel,
    autoCloseOverlay: Boolean,
    onCloseRequested: () -> Unit,
) {
    val closeIfNeeded = {
        if (autoCloseOverlay) onCloseRequested()
    }

    handleAppSettingsDestination(
        destination = destination,
        handlers =
            AppSettingsDestinationHandlers(
                onOpenSettingsDetail = { detailType ->
                    openOverlaySettingsDetail(context, detailType, onCloseRequested)
                },
                onReloadApps = { viewModel.refreshApps(showToast = true) },
                onReloadContacts = { viewModel.refreshContacts(showToast = true) },
                onReloadFiles = { viewModel.refreshFiles(showToast = true) },
                onSendFeedback = {
                    FeedbackUtils.launchFeedbackEmail(context = context, feedbackText = null)
                    closeIfNeeded()
                },
                onRateQuickSearch = {
                    launchRateQuickSearch(context)
                    closeIfNeeded()
                },
                onOpenDevelopmentPage = {
                    launchDevelopmentPage(context)
                    closeIfNeeded()
                },
                onSetDefaultAssistant = {
                    openDefaultAssistantSettings(context)
                    closeIfNeeded()
                },
                onSetDefaultLauncher = {
                    openDefaultLauncherSettings(context)
                    closeIfNeeded()
                },
                onAddHomeScreenWidget = {
                    com.myst25.quicksearch.widgets.utils.requestAddQuickSearchWidget(context)
                    closeIfNeeded()
                },
                onAddQuickSettingsTile = {
                    com.myst25.quicksearch.tile.requestAddQuickSearchTile(context)
                    closeIfNeeded()
                },
                onCreateCalendarEvent = {
                    openOverlaySettingsDetail(context, SettingsDetailType.CALENDAR_EVENTS, onCloseRequested)
                },
            ),
    )
}

private fun openOverlaySettingsDetail(
    context: Context,
    detailType: SettingsDetailType,
    onCloseRequested: () -> Unit,
) {
    OverlayModeController.openMainActivity(
        context = context,
        openSettings = true,
        settingsDetailType = detailType,
    )
    onCloseRequested()
}
