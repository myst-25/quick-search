package com.myst25.quicksearch.search.core

import android.app.Application
import android.content.Context
import com.myst25.quicksearch.app.ReleaseNotesHandler
import com.myst25.quicksearch.app.navigation.NavigationHandler
import com.myst25.quicksearch.search.appShortcuts.AppShortcutManagementHandler
import com.myst25.quicksearch.search.appShortcuts.AppShortcutSearchHandler
import com.myst25.quicksearch.search.appSettings.AppSettingsRepository
import com.myst25.quicksearch.search.appSettings.AppSettingsSearchHandler
import com.myst25.quicksearch.search.apps.AppManagementService
import com.myst25.quicksearch.search.apps.AppSearchManager
import com.myst25.quicksearch.search.apps.IconPackService
import com.myst25.quicksearch.search.calendar.CalendarManagementHandler
import com.myst25.quicksearch.search.common.PinningHandler
import com.myst25.quicksearch.search.contacts.actions.ContactActionHandler
import com.myst25.quicksearch.search.contacts.utils.ContactManagementHandler
import com.myst25.quicksearch.search.contacts.utils.MessagingHandler
import com.myst25.quicksearch.search.data.AppShortcutRepository.AppShortcutRepository
import com.myst25.quicksearch.search.data.AppsRepository
import com.myst25.quicksearch.search.data.CalendarRepository
import com.myst25.quicksearch.search.data.CustomCalendarEventRepository
import com.myst25.quicksearch.search.data.ContactRepository
import com.myst25.quicksearch.search.data.FileSearchRepository
import com.myst25.quicksearch.search.data.NotesRepository
import com.myst25.quicksearch.search.data.UserAppPreferences
import com.myst25.quicksearch.search.deviceSettings.DeviceSettingsManagementHandler
import com.myst25.quicksearch.search.deviceSettings.DeviceSettingsRepository
import com.myst25.quicksearch.search.deviceSettings.DeviceSettingsSearchHandler
import com.myst25.quicksearch.search.files.FileManagementHandler
import com.myst25.quicksearch.search.files.FileSearchHandler
import com.myst25.quicksearch.search.webSuggestions.WebSuggestionHandler
import com.myst25.quicksearch.searchEngines.AliasHandler
import com.myst25.quicksearch.searchEngines.SearchEngineManager
import com.myst25.quicksearch.searchEngines.SecondarySearchOrchestrator
import com.myst25.quicksearch.tools.aiTools.CurrencyConverterHandler
import com.myst25.quicksearch.tools.aiTools.DictionaryHandler
import com.myst25.quicksearch.tools.aiTools.WordClockHandler
import com.myst25.quicksearch.tools.calculator.CalculatorHandler
import com.myst25.quicksearch.tools.dateCalculator.DateCalculatorHandler
import com.myst25.quicksearch.tools.aiSearch.AiSearchHandler
import com.myst25.quicksearch.tools.unitConverter.UnitConverterHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

internal class SearchHandlerContainer(
    private val application: Application,
    private val appContext: Context,
    private val userPreferences: UserAppPreferences,
    private val scope: CoroutineScope,
    private val repository: AppsRepository,
    private val contactRepository: ContactRepository,
    private val fileRepository: FileSearchRepository,
    private val calendarRepository: CalendarRepository,
    private val customCalendarEventRepository: CustomCalendarEventRepository,
    private val notesRepository: NotesRepository,
    private val appShortcutRepository: AppShortcutRepository,
    private val settingsShortcutRepository: DeviceSettingsRepository,
    private val appSettingsRepository: AppSettingsRepository,
    private val permissionManager: PermissionManager,
    private val searchOperations: SearchOperations,
    private val startupDispatcher: CoroutineDispatcher,
    private val updateUiState: ((SearchUiState) -> SearchUiState) -> Unit,
    private val updateConfigState: ((SearchUiConfigState) -> SearchUiConfigState) -> Unit,
    private val refreshSecondarySearches: () -> Unit,
    private val refreshAppShortcutsState: () -> Unit,
    private val refreshAppSuggestions: () -> Unit,
    private val refreshDerivedState: () -> Unit,
    private val showToast: (Int) -> Unit,
    private val currentStateProvider: () -> SearchUiState,
    private val isLowRamDevice: Boolean,
) {
    val appManager by lazy {
        AppManagementService(userPreferences, scope, refreshAppSuggestions)
    }

    val contactManager by lazy {
        ContactManagementHandler(
            userPreferences,
            scope,
            refreshSecondarySearches,
            updateUiState,
        )
    }

    val fileManager by lazy {
        FileManagementHandler(
            userPreferences,
            scope,
            refreshSecondarySearches,
            updateUiState,
        )
    }

    val settingsManager by lazy {
        DeviceSettingsManagementHandler(
            userPreferences,
            scope,
            refreshSecondarySearches,
            updateUiState,
        )
    }

    val calendarManager by lazy {
        CalendarManagementHandler(
            userPreferences,
            scope,
            refreshSecondarySearches,
            updateUiState,
        )
    }

    val appShortcutManager by lazy {
        AppShortcutManagementHandler(
            userPreferences,
            scope,
            refreshAppShortcutsState,
            updateUiState,
        )
    }

    val searchEngineManager by lazy {
        SearchEngineManager(
            appContext,
            userPreferences,
            scope,
            updateUiState,
        )
    }

    val sectionManager by lazy {
        SectionManager(userPreferences, permissionManager, scope, updateUiState)
    }

    val iconPackHandler by lazy {
        IconPackService(application, userPreferences, scope, updateUiState)
    }

    val messagingHandler by lazy {
        MessagingHandler(application, userPreferences, updateUiState)
    }

    val releaseNotesHandler by lazy {
        ReleaseNotesHandler(application, userPreferences, updateUiState)
    }

    val pinningHandler by lazy {
        PinningHandler(
            scope = scope,
            permissionManager = permissionManager,
            contactRepository = contactRepository,
            fileRepository = fileRepository,
            notesRepository = notesRepository,
            userPreferences = userPreferences,
            uiStateUpdater = updateUiState,
        )
    }

    val webSuggestionHandler by lazy {
        WebSuggestionHandler(
            scope = scope,
            userPreferences = userPreferences,
            uiStateUpdater = updateUiState,
        )
    }

    val calculatorHandler by lazy {
        CalculatorHandler(userPreferences = userPreferences)
    }

    val unitConverterHandler by lazy {
        UnitConverterHandler(userPreferences = userPreferences)
    }

    val dateCalculatorHandler by lazy {
        DateCalculatorHandler(userPreferences = userPreferences)
    }

    val currencyConverterHandler by lazy {
        CurrencyConverterHandler(appContext, userPreferences)
    }

    val wordClockHandler by lazy { WordClockHandler(appContext, userPreferences) }

    val dictionaryHandler by lazy { DictionaryHandler(appContext, userPreferences) }

    val appSearchManager by lazy {
        AppSearchManager(
            context = appContext,
            repository = repository,
            userPreferences = userPreferences,
            scope = scope,
            onAppsUpdated = {
                refreshDerivedState()
            },
            onLoadingStateChanged = { isLoading, error ->
                updateConfigState { it.copy(isLoading = isLoading, errorMessage = error) }
            },
            showToastCallback = showToast,
            isLowRamDevice = isLowRamDevice,
        )
    }

    val settingsSearchHandler by lazy {
        DeviceSettingsSearchHandler(
            context = appContext,
            repository = settingsShortcutRepository,
            userPreferences = userPreferences,
            showToastCallback = showToast,
            isLowRamDevice = isLowRamDevice,
        )
    }

    val appShortcutSearchHandler by lazy {
        AppShortcutSearchHandler(
            repository = appShortcutRepository,
            userPreferences = userPreferences,
            isLowRamDevice = isLowRamDevice,
        )
    }

    val appSettingsSearchHandler by lazy {
        AppSettingsSearchHandler(
            repository = appSettingsRepository,
            userPreferences = userPreferences,
            isLowRamDevice = isLowRamDevice,
        )
    }

    val fileSearchHandler by lazy {
        FileSearchHandler(fileRepository = fileRepository, userPreferences = userPreferences)
    }

    val aiSearchHandler by lazy {
        AiSearchHandler(
            context = appContext,
            userPreferences = userPreferences,
            scope = scope,
            showToastCallback = showToast,
        )
    }

    val aliasHandler by lazy {
        AliasHandler(
            userPreferences = userPreferences,
            scope = scope,
            uiStateUpdater = updateUiState,
            aiSearchHandler = aiSearchHandler,
            searchTargetsProvider = { searchEngineManager.searchTargetsOrder },
        )
    }

    val unifiedSearchHandler by lazy {
        UnifiedSearchHandler(
            context = appContext,
            contactRepository = contactRepository,
            calendarRepository = calendarRepository,
            customCalendarEventRepository = customCalendarEventRepository,
            fileRepository = fileRepository,
            notesRepository = notesRepository,
            userPreferences = userPreferences,
            settingsSearchHandler = settingsSearchHandler,
            appSettingsSearchHandler = appSettingsSearchHandler,
            appShortcutSearchHandler = appShortcutSearchHandler,
            fileSearchHandler = fileSearchHandler,
            searchOperations = searchOperations,
        )
    }

    val secondarySearchOrchestrator by lazy {
        SecondarySearchOrchestrator(
            scope = scope,
            unifiedSearchHandler = unifiedSearchHandler,
            webSuggestionHandler = webSuggestionHandler,
            sectionManager = sectionManager,
            uiStateUpdater = updateUiState,
            currentStateProvider = currentStateProvider,
        )
    }

    lateinit var navigationHandler: NavigationHandler
        private set

    lateinit var contactActionHandler: ContactActionHandler
        private set

    fun initializeServices(
        getCallingApp: () -> CallingApp,
        getMessagingApp: () -> MessagingApp,
        getDirectDialEnabled: () -> Boolean,
        getHasSeenDirectDialChoice: () -> Boolean,
        getCurrentState: () -> SearchUiState,
        clearQuery: () -> Unit,
        externalNavigation: () -> Unit,
        onRequestAiSearch: (query: String, addToSearchHistory: Boolean) -> Unit,
        showFormattedToast: (Int, String?) -> Unit,
        showToastText: (Int) -> Unit,
    ) {
        navigationHandler =
            NavigationHandler(
                application = application,
                userPreferences = userPreferences,
                settingsSearchHandler = settingsSearchHandler,
                currentQueryProvider = { getCurrentState().query },
                onRequestAiSearch = onRequestAiSearch,
                onClearQuery = clearQuery,
                onExternalNavigation = externalNavigation,
                showToastCallback = showFormattedToast,
            )

        contactActionHandler =
            ContactActionHandler(
                context = application,
                userPreferences = userPreferences,
                getCallingApp = getCallingApp,
                getMessagingApp = getMessagingApp,
                getDirectDialEnabled = getDirectDialEnabled,
                getHasSeenDirectDialChoice = getHasSeenDirectDialChoice,
                getCurrentState = getCurrentState,
                uiStateUpdater = updateUiState,
                clearQuery = clearQuery,
                showToastCallback = showToastText,
            )
    }
}
