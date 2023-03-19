package com.tprobius.binformation.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.data.entities.Binfo
import com.tprobius.binformation.data.repository.BinfoApiRepository
import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.usecases.BinfoUseCases
import com.tprobius.binformation.presentation.screens.historyscreen.BinState
import com.tprobius.binformation.presentation.screens.historyscreen.HistoryScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinfoViewModel @Inject constructor(
    private val binfoApiRepository: BinfoApiRepository,
    private val binfoUseCases: BinfoUseCases
) : ViewModel() {
    private var getNumbersJob: Job? = null

    private val _binfo = MutableLiveData<Binfo>()
    val binfo: LiveData<Binfo>
        get() = _binfo

    private val _state = mutableStateOf(BinState())
    val state: State<BinState> = _state

    private var recentlyDeletedNumber: Bin? = null

    init {
        getBins()
    }

    fun onEvent(event: HistoryScreenEvent) {
        when (event) {
            is HistoryScreenEvent.DeleteBin -> {
                viewModelScope.launch {
                    binfoUseCases.deleteBin(event.bin)
                    recentlyDeletedNumber = event.bin
                }
            }
            is HistoryScreenEvent.RestoreBin -> {
                viewModelScope.launch {
                    binfoUseCases.insertBin(recentlyDeletedNumber ?: return@launch)
                    recentlyDeletedNumber = null
                }
            }
        }
    }

    private fun getBins() {
        getNumbersJob?.cancel()
        getNumbersJob = binfoUseCases.getBins()
            .onEach { bin ->
                _state.value = state.value.copy(
                    bins = bin
                )
            }
            .launchIn(viewModelScope)
    }

    fun getBinfo(number: Int) {
        viewModelScope.launch {
            binfoApiRepository.getBinfo(number).let {
                _binfo.postValue(it)
            }
        }
    }

    fun insertBin(bin: Bin) {
        viewModelScope.launch {
            binfoUseCases.insertBin(bin)
        }
    }

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}