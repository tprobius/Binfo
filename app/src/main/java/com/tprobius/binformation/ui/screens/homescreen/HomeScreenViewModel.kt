package com.tprobius.binformation.ui.screens.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.data.api.model.Binformation
import com.tprobius.binformation.data.repository.BinformationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val binformationRepository: BinformationRepository
) : ViewModel() {
    private val _state = MutableLiveData<Binformation>()
    val state: LiveData<Binformation>
        get() = _state

    init {
        viewModelScope.launch {
            val binformation = binformationRepository.getBinformation()
            _state.value = binformation
        }
    }
}