package com.myst25.quicksearch.app

import android.app.Activity
import com.myst25.quicksearch.search.data.UserAppPreferences

/** No-op for F-Droid builds (Google Play in-app updates are unavailable). */
object UpdateHelper {
    fun checkForUpdates(
        activity: Activity,
        userPreferences: UserAppPreferences,
    ) = Unit
}
