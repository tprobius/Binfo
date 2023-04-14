package com.tprobius.binformation.presentation.screens.historyscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.usecases.BinfoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val binfoUseCases: BinfoUseCases
) : ViewModel() {
    private var getNumbersJob: Job? = null
    private var recentlyDeletedNumber: Bin? = null
    private val _state = mutableStateOf(BinState())
    val state: State<BinState> = _state

    init {
        getBins()
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
}