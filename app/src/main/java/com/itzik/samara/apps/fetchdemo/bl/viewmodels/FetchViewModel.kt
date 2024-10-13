package com.itzik.samara.apps.fetchdemo.bl.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itzik.samara.apps.fetchdemo.bl.ApiService.fetchService
import com.itzik.samara.apps.fetchdemo.bl.ItemsHelper
import com.itzik.samara.apps.fetchdemo.bl.models.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FetchViewModel : ViewModel() {

    private val _items = MutableStateFlow<Map<Int, List<Item>>>(hashMapOf())
    val items = _items.asStateFlow()

    var showItems by mutableStateOf(false)

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val items = fetchService.getItems()
                val sortedItems = ItemsHelper.prepareItemsForDisplay(items)
                _items.value = sortedItems
                showItems = true
            } catch (e: Exception) {
                e.printStackTrace()
                _items.value = hashMapOf()
            }

        }
    }
}