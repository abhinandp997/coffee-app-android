package com.example.coffeeapp.ui.screens.HomeScreen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.coffeeapp.R
import com.example.coffeeapp.data.model.CoffeeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    query: String,
    suggestions: List<CoffeeItem>,
    onSuggestionsClick: (CoffeeItem) -> Unit,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var expand by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expand && suggestions.isNotEmpty(),
        onExpandedChange = { expand = !expand }
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                onQueryChange(it)
                expand = it.isNotBlank()
            },
            placeholder = {
                Text(
                    text = "Search Coffee..."
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = onSearchClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.search_icon),
                        contentDescription = null
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = modifier
        )

        ExposedDropdownMenu(
            expanded = expand && suggestions.isNotEmpty(),
            onDismissRequest = { expand = false },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            suggestions.take(5).forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(item.title)
                    },
                    onClick = {
                        expand = false
                        onSuggestionsClick(item)
                    }
                )
            }
        }
    }
}