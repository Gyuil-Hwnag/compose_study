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

/**
 * 에러 분석 설명
 * 1. tabWidths의 경우 초기에 생성되는 만큼의 Tab Width를 가진 리스트가 remember 상태값을로 설정이 된다.
 * 2. 현재의 탭 길이보다 긴 탭으로 바뀌어야 한다.
 * 3. 현재 ScrollableTabRow가 forEachIndex를 돈다.
 * 4. 그러나 tabWidths에 저장된 tab의 width 상태 리스트는 이전 tab을 기준으로 되어 있으므로, 현재탭 에서는 OutOfIndex가 발생할 수 밖에 없다.
 *
 * 해결 방법
 * 1. tabWidths를 remember로 지정을 안하고 탭을 계속 새로 그린다? <- 채택X : 탭에 클릭 이벤트도 있는데, 그럼 탭이 클릭될 때도 매번 Composable을 그릴 것인가?
 * 2. 기존 tab width 리스트의 사이즈를 넘어가는 탭에 대해서는 해당탭에서 분기처리를 해준다. <- 채택
**/

@Composable
fun BottomIndicatorTabRow(
    tabs: List<Category>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int, category: Category) -> Unit
) {
    val density = LocalDensity.current

    /**
     * Solve 코드
     **/
    var tabWidths by remember { mutableStateOf(List(tabs.size) { 0.dp }) }

    /**
     * Error 코드
     * 내용 비교하면 사실상 같은 코드인데 정리하고 안하고 차이
     * mutalbe 변수 -> mutable 변수로 수정(tabs가 변경이 되는 리스트 이므로)
     **/
//    val tabWidths = remember {
//        val tabWidthStateList = mutableStateListOf<Dp>()
//        repeat(tabs.size) { tabWidthStateList.add(0.dp) }
//        tabWidthStateList
//    }

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
                    /**
                     * Solve 코드
                     **/
                    tabWidths = tabWidths.toMutableList().apply {
                        if (index < size) this[index] = with(density) { textLayoutResult.size.width.toDp() }
                        else add(with(density) { textLayoutResult.size.width.toDp() })
                    }

                    /**
                     * Error 코드
                     * 현재 오류가 발생하는 IndexOutOfBounds가 발생하는 index >= size인 경우를 분기 처리하여 빈탭 Width를 만들어둔다
                     **/
//                    tabWidths[index] = with(density) { textLayoutResult.size.width.toDp() }
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