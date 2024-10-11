package com.jmg.baseproject.ui.selectors


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.ui.text.textFields.TfSearchRound
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.text.textView.TextLarge

@Composable
fun StringSelectorShadow(
    items: SnapshotStateList<String>,
    selected: MutableState<String>,
    modifier: Modifier = Modifier,
    showSearch: Boolean = true,
    text: String
){
    var search = remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ){
            Image(
                painter = painterResource(R.drawable.xmark),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
            )

            TextLarge(
                text = text,
                fontSize = 18,
                weight = 700,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        if (showSearch) {
            TfSearchRound(
                drawable = R.drawable.search,
                value = search,
                label = "Search",
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .fillMaxWidth()
                    .border(
                        width = .5.dp,
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                search = {

                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            items(items = if(search.value.isEmpty()){
                items
            } else {
                items.filter { it.contains(search.value, ignoreCase = true) }
            }) {
                Card(
                    elevation = CardDefaults.elevatedCardElevation(4.dp), // Adjust the elevation here
                    shape = RoundedCornerShape(10.dp), // This will give the rounded corners
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .background(MaterialTheme.colorScheme.background) // Adjust background if needed
                        .height(50.dp)
                        .clickable {
                            selected.value = it
                        },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background),
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .align(Alignment.CenterStart),
                        )
                        if (selected.value == it) {
                            Row(
                                modifier = Modifier
                                    .padding(end = 24.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .align(Alignment.CenterEnd),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(4.dp),
                                    tint = Color.Black
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StringSelectorShadowPrev(){
    StringSelectorShadow(
        items = remember { mutableStateListOf("1", "2", "3") },
        selected = remember { mutableStateOf("1") },
        modifier = Modifier.fillMaxWidth(),
        showSearch = true,
        text = "Select an item"
    )
}