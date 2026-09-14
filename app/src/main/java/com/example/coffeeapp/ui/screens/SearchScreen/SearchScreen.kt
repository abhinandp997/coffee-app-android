package com.example.coffeeapp.ui.screens.SearchScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.ui.screens.Components.CategoryMenu

@Composable
fun SearchScreen(
    query: String,
    modifier: Modifier = Modifier,
    onItemClick: (CoffeeItem) -> Unit,
    onBackClick: () -> Unit
) {
    val searchViewModel: SearchViewModel = hiltViewModel()
    val searchResults by searchViewModel.searchResults.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        searchViewModel.search(query)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardDoubleArrowLeft,
                    contentDescription = "Back"
                )
            }

            Text(
                text = "Search Results",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Results for \"$query\"",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Log.d("Search", "searchResults ${searchResults}")

        if (searchResults.isEmpty()) {

            Text(
                text = "No coffee found"
            )

        } else {

            CategoryMenu(
                items = searchResults,
                onItemClick = onItemClick,
                modifier = Modifier.weight(1f)
            )
        }
    }


}