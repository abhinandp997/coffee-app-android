package com.example.coffeeapp.ui.screens.HomeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeapp.data.model.Category
import com.example.coffeeapp.ui.screens.HomeScreen.HomeUiState
import com.example.coffeeapp.ui.screens.HomeScreen.HomeViewModel

@Composable
fun CategoryRow(
    uiState: HomeUiState,
    homeViewModel: HomeViewModel,
    categories: List<Category>,
    selectedCategoryId: Int?,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Category",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.primary
        )
        SortDropDown(
            selectedSort = uiState.selectedSort,
            onSortSelect = { homeViewModel.onSortSelected(it) }
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(
            items = categories,
            key = { it.id }
        ) { category ->
            CategoryChip(
                category = category,
                isSelected = category.id == selectedCategoryId,
                onClick = { onCategoryClick(category) }
            )
        }
    }
}