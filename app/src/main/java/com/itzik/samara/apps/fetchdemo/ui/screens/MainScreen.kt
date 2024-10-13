package com.itzik.samara.apps.fetchdemo.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itzik.samara.apps.fetchdemo.bl.viewmodels.FetchViewModel
import com.itzik.samara.apps.fetchdemo.ui.theme.FetchDemoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(innerPadding: PaddingValues) {

    val fetchViewModel = viewModel<FetchViewModel>()
    val items by fetchViewModel.items.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {

        val (loadingText) = createRefs()

        LaunchedEffect(Unit) {
            fetchViewModel.fetchItems()
        }

        if (!fetchViewModel.showItems) {
            Text(
                text = "Loading...",
                fontSize = 30.sp,
                modifier = Modifier.constrainAs(loadingText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items.forEach { (listId, items) ->
                    stickyHeader {
                        Row {
                            Text(text = "List Id: $listId", fontSize = 30.sp)
                        }
                    }
                    items(items.size) {
                        Text(text = items[it].name ?: "")
                    }
                }

            }
        }
    }


}

@Composable
@Preview
fun MainScreenPreview() {
    FetchDemoTheme {
        MainScreen(PaddingValues(10.dp))
    }
}
