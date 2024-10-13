package com.itzik.samara.apps.fetchdemo.bl

import androidx.annotation.VisibleForTesting
import com.itzik.samara.apps.fetchdemo.bl.models.Item

object ItemsHelper {

    @VisibleForTesting
    fun removeNullNames(items: List<Item>): List<Item> {
        return items.filterNot { it.name.isNullOrEmpty() }
    }

    @VisibleForTesting
    fun groupItemsById(items: List<Item>): Map<Int, List<Item>> {
        return items.groupBy { it.listId }.toSortedMap()
    }

    fun prepareItemsForDisplay(items: List<Item>): Map<Int, List<Item>> {
        val filteredItems = removeNullNames(items)
        val groupedItems = groupItemsById(filteredItems)
        val sortedItems = groupedItems.mapValues { (_, items) ->
            items.sortedWith(compareBy<Item> { it.listId }.thenBy { it.name })
        }
        return sortedItems
    }


}


