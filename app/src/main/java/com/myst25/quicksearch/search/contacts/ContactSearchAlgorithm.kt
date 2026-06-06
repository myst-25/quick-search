package com.myst25.quicksearch.search.contacts

import com.myst25.quicksearch.search.models.ContactInfo
import com.myst25.quicksearch.search.utils.DefaultSearchMatcher
import com.myst25.quicksearch.search.utils.SearchQueryContext
import java.util.Locale

object ContactSearchAlgorithm {
    fun search(
        fullList: List<ContactInfo>,
        query: String,
    ): List<ContactInfo> {
        if (fullList.isEmpty()) return emptyList()
        return search(fullList, SearchQueryContext.fromRawQuery(query))
    }

    fun search(
        fullList: List<ContactInfo>,
        queryContext: SearchQueryContext,
    ): List<ContactInfo> {
        if (fullList.isEmpty()) return emptyList()

        return fullList
            .mapNotNull { contact ->
                val priority = ContactSearchPolicy.matchPriority(contact.displayName, null, queryContext)
                if (!DefaultSearchMatcher.isMatch(priority)) {
                    null
                } else {
                    contact to priority
                }
            }.sortedWith(
                compareBy(
                    { it.second },
                    { it.first.displayName.lowercase(Locale.getDefault()) },
                ),
            ).map { it.first }
    }
}

