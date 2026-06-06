package com.myst25.quicksearch.search.searchScreen

import com.myst25.quicksearch.search.contacts.models.ContactCardAction
import com.myst25.quicksearch.search.models.ContactInfo

data class ContactActionPickerDialogState(
    val contact: ContactInfo,
    val isPrimary: Boolean,
    val currentAction: com.myst25.quicksearch.search.contacts.models.ContactCardAction?,
)