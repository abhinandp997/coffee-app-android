package com.example.coffeeapp.ui.screens.ItemScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoffeeSizeSelector(
    selectedSize: String,
    onSizeSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val sizes = listOf("Small", "Medium", "Large")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        sizes.forEach { size ->
            val selected = selectedSize == size
            Surface(
                modifier = Modifier.weight(1f),
                onClick = { onSizeSelect(size) },
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.secondary,
                border = BorderStroke(
                    width = if (selected) 2.dp else 0.dp,
                    color = if (selected) Color.Black else Color.Transparent
                )
            ) {
                Text(
                    text = size,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically),
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}