package com.itzik.samara.apps.fetchdemo

import com.itzik.samara.apps.fetchdemo.bl.ItemsHelper
import com.itzik.samara.apps.fetchdemo.bl.models.Item
import io.mockk.clearAllMocks
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ItemsHelperTest {

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `test removeNullNames`() {
        val items = listOf(
            Item(1, 1, "Item 1"),
            Item(2, 2, null),
            Item(3, 1, "Item 3")
        )
        val actual = ItemsHelper.removeNullNames(items)
        val expected = listOf(
            Item(1, 1, "Item 1"),
            Item(3, 1, "Item 3")
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `test groupItemsById`() {
        val items = listOf(
            Item(1, 1, "Item 1"),
            Item(2, 2, "Item 2"),
            Item(3, 1, "Item 3")
        )
        val actual = ItemsHelper.groupItemsById(items)
        //Map<Int, List<Item>>
        val map = mapOf(1 to listOf(Item(1, 1, "Item 1"), Item(3, 1, "Item 3")),
            2 to listOf(Item(2, 2, "Item 2")))

        assertEquals(actual,map)

    }

    @Test
    fun `test prepareItemsForDisplay`() {
        val items = listOf(
            Item(1, 1, "Item 1"),
            Item(2, 2, "Item 2"),
            Item(3, 1, "Item 3")
        )
        val actual = ItemsHelper.prepareItemsForDisplay(items)
        val map = mapOf(1 to listOf(Item(1, 1, "Item 1"), Item(3, 1, "Item 3")),
            2 to listOf(Item(2, 2, "Item 2")))

        assertEquals(actual,map)

    }

}