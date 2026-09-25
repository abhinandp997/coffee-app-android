package com.example.coffeeapp.ui.screens.Cart.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CartSummary(
    subtotal: Double,
    deliveryFee: Double,
    tax: Double,
    discount: Double = 0.0,
    modifier: Modifier = Modifier
) {
    val total = subtotal + deliveryFee + tax - discount
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                MaterialTheme.colorScheme.surface
            )
            .padding(16.dp)
    ) {
        SummaryRow(
            title = "Subtotal",
            value = subtotal,
        )

        Spacer(modifier = Modifier.height(8.dp))

        SummaryRow(
            title = "Delivery Fee",
            value = deliveryFee,
        )

        Spacer(modifier = Modifier.height(8.dp))

        SummaryRow(
            title = "Total tax",
            value = tax,
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (discount > 0) {
            SummaryRow(
                title = "Discount",
                value = discount,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Total",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "AED %.2f".format(total),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}