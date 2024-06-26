package com.jmg.baseproject.ui.selectors

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.ui.text.textFields.TfSearchRound
import com.jmg.baseproject.R

@Composable
fun StringSelector(
    items: SnapshotStateList<String>,
    selected: MutableState<String>,
    modifier: Modifier = Modifier
){
    var search = remember { mutableStateOf("") }
    Column(
        modifier = modifier
    ) {
        TfSearchRound(
            drawable = R.drawable.search,
            value = search,
            label = "Search",
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth()
                .border(width = .5.dp, shape = RoundedCornerShape(50), color = Color.Black),
            search = {

            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(items = items) {
                Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .border(width = 1.dp, color = MaterialTheme.colorScheme.onBackground, shape = RoundedCornerShape(50))
                ) {
                    Text(text = it,
                        modifier = Modifier
                        )
                }
            }
        }
    }
}

@Preview
@Composable
fun StringSelectorPrev(){
    StringSelector(
        items = remember { mutableStateListOf("1", "2", "3") },
        selected = remember { mutableStateOf("1") },
        modifier = Modifier.fillMaxWidth()
    )
}