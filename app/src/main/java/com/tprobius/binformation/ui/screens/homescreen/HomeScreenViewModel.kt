package com.tprobius.binformation.ui.screens.homescreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.binformation.data.api.model.Binformation
import com.tprobius.binformation.data.repository.BinformationApiRepository
import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.use_cases.BinformationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val binformationApiRepository: BinformationApiRepository,
    private val binformationUseCases: BinformationUseCases
) : ViewModel() {
    private val _binformation = MutableLiveData<Binformation>()
    val binformation: LiveData<Binformation>
        get() = _binformation

    private val _number = MutableLiveData<Int>()
    val number: LiveData<Int>
        get() = _number

    init {
        number.value?.let { getBinformation(it) }
    }

    fun getBinformation(number: Int) {
        viewModelScope.launch {
            binformationApiRepository.getBinformation(number).let {
                _binformation.postValue(it)
            }
        }
    }

    fun insertNumber(number: Bins) {
        viewModelScope.launch(Dispatchers.IO) {
            binformationUseCases.insertNumber(number)
        }
    }

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}