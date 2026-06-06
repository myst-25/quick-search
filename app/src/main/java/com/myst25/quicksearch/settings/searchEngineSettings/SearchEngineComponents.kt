package com.myst25.quicksearch.settings.searchEnginesScreen

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import com.myst25.quicksearch.shared.ui.theme.AppColors

/**
 * Reusable divider component with consistent styling.
 */
@Composable
fun SearchEngineDivider() {
    HorizontalDivider(color = AppColors.SettingsDivider)
}
