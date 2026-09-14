package com.example.coffeeapp.ui.screens.ItemScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityDecrease: () -> Unit,
    onQuantityIncrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onQuantityDecrease,
            modifier = Modifier.size(28.dp)
        ) {
            Text("-")
        }

        Text(
            text = quantity.toString(),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        IconButton(
            onClick = onQuantityIncrease,
            modifier = Modifier.size(28.dp)
        ) {
            Text("+")
        }
    }
}