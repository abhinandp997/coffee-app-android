package com.example.coffeeapp.ui.screens.HomeScreen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coffeeapp.data.model.PopularItem
import kotlinx.coroutines.delay

@Composable
fun Carousel(
    items: List<PopularItem>,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { items.size })

    LaunchedEffect(pagerState, items.size) {
        if (items.size > 1) {
            while (true) {
                val nextPage = (pagerState.currentPage + 1) % items.size
                delay(3000L)
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 24.dp),
        pageSpacing = 12.dp,
        modifier = modifier
    ) { page ->
        CarouselCard(
            item = items[page]
        )
    }
}