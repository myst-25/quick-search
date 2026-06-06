package com.myst25.quicksearch.search.files

import com.myst25.quicksearch.search.utils.SearchQueryContext
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FileSearchPolicyTest {
    @Test
    fun multiWordQueryRejectsFileWhenAQueryTokenIsNotCovered() {
        val covered =
            FileSearchPolicy.areAllQueryTokensCovered(
                query = SearchQueryContext.fromRawQuery("teja passport"),
                displayName = "teja_notes.txt",
                nickname = null,
                fuzzyMinScore = 72,
                fuzzyMaxEditDistance = 2,
            )

        assertFalse(covered)
    }

    @Test
    fun multiWordQueryCanBeCoveredByNickname() {
        val covered =
            FileSearchPolicy.areAllQueryTokensCovered(
                query = SearchQueryContext.fromRawQuery("teja passport"),
                displayName = "teja_notes.txt",
                nickname = "passport docs",
                fuzzyMinScore = 72,
                fuzzyMaxEditDistance = 2,
            )

        assertTrue(covered)
    }
}
