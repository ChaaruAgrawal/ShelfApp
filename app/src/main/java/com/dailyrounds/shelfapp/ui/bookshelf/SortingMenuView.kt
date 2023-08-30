package com.dailyrounds.shelfapp.ui.bookshelf

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dailyrounds.shelfapp.common.SortKey

/**
 * Show menu for sorting keys
 */
@Composable
fun SortingMenuView(bookShelfViewModel: BookShelfViewModel) {
    val selectedKey = rememberSaveable { mutableStateOf(SortKey.TITLE) }
    val ascendingOrder = rememberSaveable { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Sort by: ")
            SortKey.values().forEach {
                Row(
                    modifier = Modifier.padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (it == selectedKey.value),
                        onClick = {
                            selectedKey.value = it
                            bookShelfViewModel.sortBookShelf(selectedKey.value, ascendingOrder.value)
                        }
                    )
                    Text(text = SortKey.getValue(it))
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Ascending Order")
            Switch(
                checked = ascendingOrder.value,
                onCheckedChange = {
                    ascendingOrder.value = !ascendingOrder.value
                    bookShelfViewModel.sortBookShelf(selectedKey.value, ascendingOrder.value)
                }
            )
        }
    }
}