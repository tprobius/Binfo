package com.tprobius.binformation.presentation.screens.historyscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.usecases.DeleteBin
import com.tprobius.binformation.domain.usecases.GetBins
import com.tprobius.binformation.domain.usecases.InsertBin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val getBinsUseCase: GetBins,
    private val deleteBinUseCase: DeleteBin,
    private val insertBinUseCase: InsertBin
) : ViewModel() {
    private var recentlyDeletedNumber: Bin? = null

    private val _bins = MutableLiveData<List<Bin>>()
    val bins: LiveData<List<Bin>> = _bins

    init {
        getBins()
    }

    private fun getBins() {
        viewModelScope.launch {
            getBinsUseCase().let {
                _bins.postValue(it)
            }
        }
    }

    fun onEvent(event: HistoryScreenEvent) {
        when (event) {
            is HistoryScreenEvent.DeleteBin -> {
                viewModelScope.launch {
                    deleteBinUseCase(event.bin)
                    recentlyDeletedNumber = event.bin
                }
            }
            is HistoryScreenEvent.RestoreBin -> {
                viewModelScope.launch {
                    insertBinUseCase(recentlyDeletedNumber ?: return@launch)
                    recentlyDeletedNumber = null
                }
            }
        }
    }
}