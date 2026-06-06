package com.myst25.quicksearch.search.calendar

import com.myst25.quicksearch.search.core.CalendarEventManagementConfig
import com.myst25.quicksearch.search.core.GenericManagementHandler
import com.myst25.quicksearch.search.core.ManagementHandler
import com.myst25.quicksearch.search.core.SearchUiState
import com.myst25.quicksearch.search.data.UserAppPreferences
import com.myst25.quicksearch.search.models.CalendarEventInfo
import kotlinx.coroutines.CoroutineScope

class CalendarManagementHandler(
    userPreferences: UserAppPreferences,
    scope: CoroutineScope,
    onStateChanged: () -> Unit,
    onUiStateUpdate: ((SearchUiState) -> SearchUiState) -> Unit,
) : ManagementHandler<CalendarEventInfo> by GenericManagementHandler(
    config = CalendarEventManagementConfig(),
    userPreferences = userPreferences,
    scope = scope,
    onStateChanged = onStateChanged,
    onUiStateUpdate = onUiStateUpdate,
)
