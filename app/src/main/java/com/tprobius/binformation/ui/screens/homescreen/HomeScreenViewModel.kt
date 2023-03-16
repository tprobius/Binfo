package com.tprobius.binformation.ui.screens.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.data.api.model.Binformation
import com.tprobius.binformation.data.repository.BinformationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val binformationRepository: BinformationRepository
) : ViewModel() {
    private val _binformation = MutableLiveData<Binformation>()
    val binformation: LiveData<Binformation>
        get() = _binformation

    init {
        getBinformation()
    }

    fun getBinformation() {
        viewModelScope.launch {
            binformationRepository.getBinformation().let {
                _binformation.postValue(it)
            }
        }
    }
}