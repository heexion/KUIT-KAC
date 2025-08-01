package com.konkuk.kuit_kac.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp

@Composable
fun VerticalScrollbar(
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
    trackColor: Color = Color(0x33000000),
    thumbColor: Color = Color(0xFFAAAAAA),
) {
    var containerHeightPx by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .width(6f.wp())
            .onGloballyPositioned {
                containerHeightPx = it.size.height
            }
            .background(trackColor, shape = RoundedCornerShape(3f.bhp()))
    ) {
        val layoutInfo = scrollState.layoutInfo
        val totalItems = layoutInfo.totalItemsCount
        val visibleItems = layoutInfo.visibleItemsInfo.size

        if (totalItems > 0 && visibleItems > 0 && containerHeightPx > 0) {
            val thumbHeightRatio = visibleItems.toFloat() / totalItems.toFloat()
            val thumbHeight = (containerHeightPx * thumbHeightRatio).toInt()
            val scrollProgress = scrollState.firstVisibleItemIndex.toFloat() /
                    (totalItems - visibleItems).coerceAtLeast(1)

            val maxOffset = containerHeightPx - thumbHeight
            val offsetY = (maxOffset * scrollProgress).toInt()

            Box(
                modifier = Modifier
                    .width(6f.wp())
                    .offset { IntOffset(x = 0, y = offsetY) }
                    .height(with(LocalDensity.current) { thumbHeight.toDp() })
                    .background(thumbColor, shape = RoundedCornerShape(3f.bhp()))
                    .border(
                        width = 1.25.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 30f.bhp())
                    )
            )
        }
    }
}