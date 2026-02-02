package com.mhd_07.bloodsugar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhd_07.bloodsugar.domain.model.PressureMeasure
import com.mhd_07.bloodsugar.domain.model.SugarMeasure
import com.mhd_07.bloodsugar.domain.usecase.DeleteHealthIndicator
import com.mhd_07.bloodsugar.domain.usecase.GetHealthIndicators
import com.mhd_07.bloodsugar.domain.usecase.PressureMeasureUseCases
import com.mhd_07.bloodsugar.domain.usecase.SugarMeasureUseCases
import com.mhd_07.bloodsugar.domain.usecase.UpsertHealthIndicator
import com.mhd_07.bloodsugar.domain.usecase.UserUseCases
import com.mhd_07.bloodsugar.presentation.model.PressureMeasuresData
import com.mhd_07.bloodsugar.presentation.model.SearchState
import com.mhd_07.bloodsugar.presentation.model.SearchType
import com.mhd_07.bloodsugar.presentation.model.SugarMeasuresData
import com.mhd_07.bloodsugar.presentation.model.UIIntents
import com.mhd_07.bloodsugar.presentation.model.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val userUseCases: UserUseCases,
    private val sugarMeasureUseCases: SugarMeasureUseCases,
    private val pressureMeasureUseCases: PressureMeasureUseCases,
    private val getHealthIndicators: GetHealthIndicators,
    private val deleteHealthIndicator: DeleteHealthIndicator,
    private val upsertHealthIndicator: UpsertHealthIndicator,
) : ViewModel() {

    //search
    private val _isSearching = MutableStateFlow(false)
    private val _searchQuery = MutableStateFlow("")
    private val _dateRange = MutableStateFlow(Pair(0L, 0L))
    private val _searchType = MutableStateFlow(SearchType.SUGAR)

    private val _searchState = combine(
        _searchQuery,
        _dateRange,
        _searchType,
        _isSearching
    ) { query, dateRange, type, isSearching ->
        SearchState(query, dateRange, type, isSearching)
    }

    //Sugar
    private val _selectedSugarMeasure = MutableStateFlow<SugarMeasure?>(null)
    private val _sugarMeasures = combine(
        _isSearching,
        _searchQuery,
        _dateRange,
        _searchType
    ) { isSearching, query, dateRange, type ->
        Triple(isSearching, query, dateRange) to type
    }.flatMapLatest { (searchData, type) ->
        val (isSearching, query, dateRange) = searchData
        if (isSearching && type == SearchType.SUGAR)
            sugarMeasureUseCases.searchSugarMeasure(query, dateRange)
        else
            sugarMeasureUseCases.getSugarMeasures()
    }
    private val _latestSugarMeasureFlow = sugarMeasureUseCases.getLatestSugarMeasure().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        null
    )
    private val _sugarMeasuresData = combine(
        _sugarMeasures,
        _latestSugarMeasureFlow,
        _selectedSugarMeasure
    ) { list, latest, selected -> SugarMeasuresData(list, latest, selected) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            SugarMeasuresData()
        )

    //Pressures
    private val _selectedPressureMeasure = MutableStateFlow<PressureMeasure?>(null)
    private val _pressureMeasures = combine(
        _isSearching,
        _searchQuery,
        _dateRange,
        _searchType
    ) { isSearching, query, dateRange, type ->
        Triple(isSearching, query, dateRange) to type
    }.flatMapLatest { (searchData, type) ->
        val (isSearching, query, dateRange) = searchData
        if (isSearching && type == SearchType.PRESSURE)
            pressureMeasureUseCases.searchPressureMeasures(query, dateRange)
        else
            pressureMeasureUseCases.getPressureMeasures()
    }
    private val _latestPressureMeasureFlow = pressureMeasureUseCases.getLatestPressureMeasure().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        null
    )
    private val _pressureMeasuresData = combine(
        _pressureMeasures,
        _latestPressureMeasureFlow,
        _selectedPressureMeasure
    ) { list, latest, selected ->
        PressureMeasuresData(list, latest, selected)
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            PressureMeasuresData()
        )


    private val _healthIndicators = 
        getHealthIndicators().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    //Users
    private val _users = 
        userUseCases.getUsers().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _selectedUser = userUseCases.getSelectedUser().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        null
    )
    private val _userState = combine(_users, _selectedUser) { users, selectedUser ->
        users to selectedUser
    }

    val uiState = combine(
        _userState,
        _sugarMeasuresData,
        _pressureMeasuresData,
        _healthIndicators,
        _searchState
    ) { (users, selectedUser), sugarMeasuresData, pressureMeasuresData, healthIndicators, searchState ->
        UIState(
            users = users,
            sugarMeasures = sugarMeasuresData.sugarMeasures,
            pressureMeasures = pressureMeasuresData.pressureMeasures,
            healthIndicators = healthIndicators,
            selectedUser = selectedUser,
            latestSugarMeasure = sugarMeasuresData.latestSugarMeasure,
            latestPressureMeasure = pressureMeasuresData.latestPressureMeasure,
            searchQuery = searchState.searchQuery,
            dateRange = searchState.dateRange,
            searchType = searchState.searchType,
            isSearching = searchState.isSearching,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        UIState()
    )

    fun onEvent(event: UIIntents) {
        when (event) {
            is UIIntents.DeleteHealthIndicator -> viewModelScope.launch(Dispatchers.IO) {
                deleteHealthIndicator(event.id)
            }

            is UIIntents.DeleteSugarMeasure -> viewModelScope.launch(Dispatchers.IO) {
                sugarMeasureUseCases.deleteSugarMeasure(event.id)
            }

            is UIIntents.DeleteUser -> viewModelScope.launch(Dispatchers.IO) {
                userUseCases.deleteUser(event.id)
            }

            is UIIntents.DeletePressureMeasure -> viewModelScope.launch(Dispatchers.IO) {
                pressureMeasureUseCases.deletePressureMeasure(event.id)
            }

            is UIIntents.UpsertHealthIndicator -> viewModelScope.launch(Dispatchers.IO) {
                upsertHealthIndicator(event.healthIndicator)
            }

            is UIIntents.UpsertPressureMeasure -> viewModelScope.launch(Dispatchers.IO) {
                pressureMeasureUseCases.upsertPressureMeasure(event.pressureMeasure)
            }

            is UIIntents.UpsertSugarMeasure -> viewModelScope.launch(Dispatchers.IO) {
                sugarMeasureUseCases.upsertSugarMeasure(event.sugarMeasure)
            }

            is UIIntents.UpsertUser -> viewModelScope.launch(Dispatchers.IO) {
                userUseCases.upsertUser(event.user)
            }

            is UIIntents.SelectPressureMeasure -> {
                _selectedPressureMeasure.value = event.measure
            }

            is UIIntents.SelectSugarMeasure -> {
                _selectedSugarMeasure.value = event.measure
            }

            is UIIntents.SelectUser -> {
                viewModelScope.launch(Dispatchers.IO) {
                    userUseCases.login(event.id)
                }
            }
        }
    }
}
