package com.example.coffeeapp.ui.screens.HomeScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeapp.ui.screens.HomeScreen.SortOptions

@Composable
fun SortDropDown(
    selectedSort: SortOptions,
    onSortSelect: (SortOptions) -> Unit,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Sort By") }

    val options = listOf(
        "Price: Low to High",
        "Price: High to Low",
        "Rating: High to Low",
        "Name: A-Z"
    )

    Box(
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                selectedOption,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            SortOptions.entries.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.title) },
                    onClick = {
                        selectedOption = option.title
                        onSortSelect(option)
                        expanded = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }

}