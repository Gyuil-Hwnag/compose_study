package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tab(
    tabs: List<Category>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int, category: Category) -> Unit
) {
    val density = LocalDensity.current
    var tabWidths by remember { mutableStateOf(List(tabs.size) { 0.dp }) }

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
//                    tabWidth = tabWidths.getOrNull(selectedTabIndex) ?: 0.dp
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
                    tabWidths = tabWidths.toMutableList().apply {
                        if (index < size) this[index] = with(density) { textLayoutResult.size.width.toDp() }
                        else add(with(density) { textLayoutResult.size.width.toDp() })
                    }
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