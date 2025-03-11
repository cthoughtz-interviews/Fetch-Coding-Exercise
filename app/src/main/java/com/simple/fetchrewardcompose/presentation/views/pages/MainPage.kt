package com.simple.fetchrewardcompose.presentation.views.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simple.fetchrewardcompose.FetchResult
import com.simple.fetchrewardcompose.domain.model.ItemsResponse
import com.simple.fetchrewardcompose.presentation.viewmodel.ItemsViewModel

private const val TAG = "MainPage"

@Composable
fun MainPage(
    itemsViewModel: ItemsViewModel
) {

    val getItemResult = itemsViewModel.itemResultFlow.collectAsStateWithLifecycle()

    GetData(
        getItemResult.value
    )
}

@Composable
fun GetData(fetchData: FetchResult<ItemsResponse>) {
    when (fetchData) {
        is FetchResult.Failure -> Text(text = fetchData.errorMessage)
        is FetchResult.Loading -> CircularProgressIndicator()
        is FetchResult.Success -> {
            Log.d(TAG, "showData: ${fetchData.data}")
            ShowDataOnScreen(fetchData.data)
        }

        else -> Log.d(TAG, "showData: Something Went wrong!!")
    }
}

@Composable
fun ShowDataOnScreen(data: ItemsResponse) {

    val displayItems = data
        .filter { it.name?.isNotBlank() == true }
        .groupBy { it.listId }
        .toSortedMap(compareBy { it })
        .mapValues { (_, items) -> items.sortedBy { it.name } }


    Log.d(TAG, "ShowDataOnScreen: $displayItems")

    LazyColumn {
        displayItems.forEach { (listId, items) ->
            item {
                Text(
                    text = "listId: $listId",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(10.dp)
                )
            }

            items(items) { item ->
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                ) {
                    Text(
                        text = "id: ${item.id}, name: ${item.name}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
