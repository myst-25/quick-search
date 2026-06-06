package com.myst25.quicksearch.search.contacts

import com.myst25.quicksearch.search.utils.DefaultSearchMatcher
import com.myst25.quicksearch.search.utils.FuzzyMatcher
import com.myst25.quicksearch.search.utils.SearchMatcher
import com.myst25.quicksearch.search.utils.SearchQueryContext
import com.myst25.quicksearch.search.utils.SearchTextNormalizer

object ContactSearchPolicy {
    fun matchPriority(
        displayName: String,
        nickname: String?,
        query: SearchQueryContext,
        matcher: SearchMatcher = DefaultSearchMatcher,
    ): Int = matcher.match(primaryText = displayName, query = query, nickname = nickname)

    fun areAllQueryTokensCovered(
        query: SearchQueryContext,
        displayName: String,
        nickname: String?,
        fuzzyMinScore: Int,
        fuzzyMaxEditDistance: Int,
    ): Boolean {
        if (query.tokens.size <= 1) return true

        val normalizedDisplayName = SearchTextNormalizer.normalizeForSearch(displayName)
        val normalizedNickname = nickname?.let(SearchTextNormalizer::normalizeForSearch)

        return query.tokens.all { token ->
            isTokenCovered(
                token = token,
                normalizedPrimary = normalizedDisplayName,
                normalizedSecondary = normalizedNickname,
                fuzzyMinScore = fuzzyMinScore,
                fuzzyMaxEditDistance = fuzzyMaxEditDistance,
            )
        }
    }

    private fun isTokenCovered(
        token: String,
        normalizedPrimary: String,
        normalizedSecondary: String?,
        fuzzyMinScore: Int,
        fuzzyMaxEditDistance: Int,
    ): Boolean {
        if (normalizedPrimary.contains(token)) return true
        if (!normalizedSecondary.isNullOrBlank() && normalizedSecondary.contains(token)) return true

        val fuzzyScore =
            FuzzyMatcher.score(
                query = token,
                primaryTarget = normalizedPrimary,
                secondaryTarget = normalizedSecondary,
                maxEditDistance = fuzzyMaxEditDistance,
            )
        return fuzzyScore >= fuzzyMinScore
    }
}
