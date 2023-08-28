package com.tprobius.binformation.presentation.screens.searchscreen

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
import com.tprobius.binformation.domain.usecases.InsertBin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val binfoApiRepository: BinfoApiRepository,
    private val insertBinUseCase: InsertBin
) : ViewModel() {
    private val _binfo = MutableLiveData<Binfo>()
    val binfo: LiveData<Binfo>
        get() = _binfo

    fun getBinfo(number: Int) {
        viewModelScope.launch {
            try {
                binfoApiRepository.getBinfo(number).let {
                    _binfo.postValue(it)
                }
            } catch (e: HttpException) {
                e.message()
            }
        }
    }

    fun getBinfo(bin: Bin?) {
        if (bin != null) {
            getBinfo(bin.number)
        } else {
            _binfo.postValue(null)
        }
    }

    fun insertBin(bin: Bin) {
        viewModelScope.launch {
            insertBinUseCase(bin)
        }
    }

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}