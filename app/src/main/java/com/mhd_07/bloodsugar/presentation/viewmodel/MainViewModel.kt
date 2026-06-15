package com.mhd_07.bloodsugar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhd_07.bloodsugar.domain.model.SugarMeasure
import com.mhd_07.bloodsugar.domain.usecase.DeleteHealthIndicator
import com.mhd_07.bloodsugar.domain.usecase.GetHealthIndicators
import com.mhd_07.bloodsugar.domain.usecase.SugarMeasureUseCases
import com.mhd_07.bloodsugar.domain.usecase.UpsertHealthIndicator
import com.mhd_07.bloodsugar.domain.usecase.UserUseCases
import com.mhd_07.bloodsugar.presentation.model.SearchState
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
    private val getHealthIndicators: GetHealthIndicators,
    private val deleteHealthIndicator: DeleteHealthIndicator,
    private val upsertHealthIndicator: UpsertHealthIndicator,
) : ViewModel() {

    //search
    private val _isSearching = MutableStateFlow(false)
    private val _searchQuery = MutableStateFlow("")
    private val _dateRange = MutableStateFlow(Pair(0L, 0L))

    private val _searchState = combine(
        _searchQuery,
        _dateRange,
        _isSearching
    ) { query, dateRange, isSearching ->
        SearchState(query, dateRange, isSearching)
    }

    //Sugar
    private val _selectedSugarMeasure = MutableStateFlow<SugarMeasure?>(null)
    private val _sugarMeasures = combine(
        _isSearching,
        _searchQuery,
        _dateRange,
    ) { isSearching, query, dateRange ->
        Triple(isSearching, query, dateRange)
    }.flatMapLatest { searchData ->
        val (isSearching, query, dateRange) = searchData
        if (isSearching)
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
        _healthIndicators,
        _searchState
    ) { (users, selectedUser), sugarMeasuresData, healthIndicators, searchState ->
        UIState(
            users = users,
            sugarMeasures = sugarMeasuresData.sugarMeasures,
            healthIndicators = healthIndicators,
            selectedUser = selectedUser,
            latestSugarMeasure = sugarMeasuresData.latestSugarMeasure,
            searchQuery = searchState.searchQuery,
            dateRange = searchState.dateRange,
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

            is UIIntents.UpsertHealthIndicator -> viewModelScope.launch(Dispatchers.IO) {
                upsertHealthIndicator(event.healthIndicator)
            }

            is UIIntents.UpsertSugarMeasure -> viewModelScope.launch(Dispatchers.IO) {
                sugarMeasureUseCases.upsertSugarMeasure(event.sugarMeasure)
            }

            is UIIntents.UpsertUser -> viewModelScope.launch(Dispatchers.IO) {
                userUseCases.upsertUser(event.user)
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
