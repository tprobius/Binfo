package com.tprobius.binformation.presentation.screens.historyscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.usecases.BinfoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val binfoUseCases: BinfoUseCases
) : ViewModel() {
    //    private var getNumbersJob: Job? = null
    private var recentlyDeletedNumber: Bin? = null
//    private val _state = mutableStateOf(BinState())
//    val state: State<BinState> = _state

    private val _bins = MutableLiveData<List<Bin>>()
    val bins: LiveData<List<Bin>> = _bins

    init {
        getBins()
    }

    fun getBins() {
        viewModelScope.launch {
            binfoUseCases.getBins().let {
                _bins.postValue(it)
            }
        }
    }

//    private fun getBins() {
//        getNumbersJob?.cancel()
//        getNumbersJob = binfoUseCases.getBins()
//            .onEach { bin ->
//                _state.value = state.value.copy(
//                    bins = bin
//                )
//            }
//            .launchIn(viewModelScope)
//    }

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