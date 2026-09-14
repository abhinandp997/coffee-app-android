package com.example.coffeeapp.ui.screens.HomeScreen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.coffeeapp.data.model.PopularItem

@Composable
fun CarouselCard(
    item: PopularItem,
    modifier: Modifier = Modifier
) {
    Log.d("PopulaItem", "Item image url ${item.picUrl}")
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column {
            AsyncImage(
                model = item.picUrl.firstOrNull(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop,
                onLoading = {
                    Log.d("CarouselCard", "Loading started for ${item.picUrl.firstOrNull()}")
                },
                onSuccess = {
                    Log.d("CarouselCard", "SUCCESS loading ${item.picUrl.firstOrNull()}")
                },
                onError = { errorState ->
                    Log.e(
                        "CarouselCard",
                        "FAILED loading ${item.picUrl.firstOrNull()}",
                        errorState.result.throwable
                    )
                }
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "★ ${item.rating}"
                    )

                    Text(
                        text = "AED ${item.price}"
                    )
                }
            }
        }
    }
}