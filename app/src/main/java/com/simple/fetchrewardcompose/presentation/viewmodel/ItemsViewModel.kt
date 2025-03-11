package com.simple.fetchrewardcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.fetchrewardcompose.FetchResult
import com.simple.fetchrewardcompose.domain.model.ItemsResponse
import com.simple.fetchrewardcompose.domain.usecase.RetrieveItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.simple.fetchrewardcompose.FetchResult.*
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemsUseCase: RetrieveItemsUseCase
) : ViewModel() {

    private val _itemsResultFlow = MutableStateFlow<FetchResult<ItemsResponse>>(None)
    val itemResultFlow = _itemsResultFlow.asStateFlow()

    init {
        getItems()
    }

    fun getItems() {
        _itemsResultFlow.update { Loading }
        viewModelScope.launch {
            val result = itemsUseCase.retrieveItems()
            _itemsResultFlow.update { result }
        }
    }
}