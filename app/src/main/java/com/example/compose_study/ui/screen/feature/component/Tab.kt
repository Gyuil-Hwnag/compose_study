package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tab(
    tabs: List<Category>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int, category: Category) -> Unit
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) { tabWidthStateList.add(0.dp) }
        tabWidthStateList
    }

    ScrollableTabRow(
        modifier = Modifier.padding(8.dp),
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        backgroundColor = Color.Transparent,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex],
                ),
                color = Color.Black,
                height = TabRowDefaults.IndicatorHeight * 0.5F
            )
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, tab ->
            Text(
                modifier = Modifier.padding(8.dp).clickable { onTabClicked(index, tab) },
                text = tab.name,
                onTextLayout = { textLayoutResult ->
                    tabWidths[index] = with(density) { textLayoutResult.size.width.toDp() }
                },
                color = Color.Black,
                fontSize = 15.sp
            )
        }
    }
}

data class Category(
    val name: String
)