package com.tprobius.binformation.ui.screens.requesthistoryscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.use_cases.BinformationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestHistoryScreenViewModel @Inject constructor(
    private val binformationUseCases: BinformationUseCases
) : ViewModel() {
    private val _state = mutableStateOf(BinState())
    val state: State<BinState> = _state
    private var recentlyDeletedNumber: Bins? = null
    private var getNumbersJob: Job? = null

    init {
        getNumbers()
    }

    fun onEvent(event: RequestHistoryEvent) {
        when (event) {
            is RequestHistoryEvent.DeleteNote -> {
                viewModelScope.launch {
                    binformationUseCases.deleteNumber(event.bin)
                    recentlyDeletedNumber = event.bin
                }
            }
            is RequestHistoryEvent.RestoreNote -> {
                viewModelScope.launch {
                    binformationUseCases.insertNumber(recentlyDeletedNumber ?: return@launch)
                    recentlyDeletedNumber = null
                }
            }
        }
    }

    private fun getNumbers() {
        getNumbersJob?.cancel()
        getNumbersJob = binformationUseCases.getNumbers()
            .onEach { bin ->
                _state.value = state.value.copy(
                    bins = bin
                )
            }
            .launchIn(viewModelScope)
    }


}