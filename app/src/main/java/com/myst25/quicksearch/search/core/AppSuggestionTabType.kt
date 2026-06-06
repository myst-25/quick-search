package com.myst25.quicksearch.search.core

enum class AppSuggestionTabType {
    NEW_UPDATED,
    PINNED,
    RECENTS,
    ALL_APPS,
    ;

    companion object {
        val DefaultEnabledTabs: Set<AppSuggestionTabType> =
            setOf(NEW_UPDATED, PINNED, RECENTS, ALL_APPS)

        fun parseEnabledTabs(rawValues: Set<*>?): Set<AppSuggestionTabType> {
            if (rawValues == null) return DefaultEnabledTabs
            val tabs =
                rawValues
                .mapNotNull { value -> 
                    (value as? String)?.let { 
                        if (it == "MOST_USED") ALL_APPS 
                        else runCatching { valueOf(it) }.getOrNull() 
                    } 
                }
                .toSet()
            return tabs.ifEmpty { DefaultEnabledTabs }
        }
    }
}
