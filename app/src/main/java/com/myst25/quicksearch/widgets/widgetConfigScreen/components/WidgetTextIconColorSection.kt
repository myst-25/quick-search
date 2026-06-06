package com.myst25.quicksearch.widgets.WidgetConfigScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.myst25.quicksearch.R
import com.myst25.quicksearch.widgets.WidgetConfigScreen.components.TextIconColorChoiceSegmentedButtonRow
import com.myst25.quicksearch.widgets.utils.TextIconColorOverride
import com.myst25.quicksearch.widgets.utils.WidgetConfigConstants
import com.myst25.quicksearch.widgets.utils.WidgetPreferences

@Composable
fun WidgetTextIconColorSection(
    state: WidgetPreferences,
    onStateChange: (WidgetPreferences) -> Unit,
) {
    Column(
        verticalArrangement =
            Arrangement.spacedBy(WidgetConfigConstants.COLOR_SECTION_SPACING),
    ) {
        Text(
            text = stringResource(R.string.widget_text_icon_color),
            style = MaterialTheme.typography.titleSmall,
        )
        TextIconColorChoiceSegmentedButtonRow(
            selectedOverride = state.textIconColorOverride,
            onSelectionChange = {
                onStateChange(state.copy(textIconColorOverride = it))
            },
        )
    }
}