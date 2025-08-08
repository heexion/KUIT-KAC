package com.konkuk.kuit_kac.core.util.context

import androidx.compose.foundation.pager.PagerState

fun PagerState.offsetForPage(page: Int): Float =
    (currentPage - page) + currentPageOffsetFraction