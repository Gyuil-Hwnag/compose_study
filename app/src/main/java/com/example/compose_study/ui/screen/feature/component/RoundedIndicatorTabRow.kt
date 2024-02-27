package com.example.compose_study.ui.screen.feature.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.compose_study.utils.noRippleClickable

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
fun RoundedIndicatorTabRow(
    tabs: List<Category>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int, category: Category) -> Unit
) {
    val density = LocalDensity.current
    var tabWidths by remember { mutableStateOf(List(tabs.size) { 0.dp }) }

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, selectedTabIndex)
    }

    ScrollableTabRow(
        modifier = Modifier.padding(8.dp),
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        backgroundColor = Color.Transparent,
        indicator = indicator,
        divider = {}
    ) {
        tabs.forEachIndexed { index, tab ->
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .noRippleClickable { onTabClicked(index, tab) },
                text = tab.name,
                onTextLayout = { textLayoutResult ->
                    tabWidths = tabWidths.toMutableList().apply {
                        if (index < size) this[index] = with(density) { textLayoutResult.size.width.toDp() }
                        else add(with(density) { textLayoutResult.size.width.toDp() })
                    }
                },
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


/**
 * dampingRatio : 튕기는 비율 낮을수록 스프링이 앞뒤로 움직임 <- 기존 모양을 유지하는 보존력
 * stiffness : 원대대로 돌아가려고 하는 힘 <- 낮을수록 속도 감속(복원력)
 **/
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    val transition = updateTransition(selectedTabIndex, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = { spring(dampingRatio = 1f, stiffness = 300f) },
        label = "",
        targetValueByState = { tabPositions[it].left }
    )
    val indicatorEnd by transition.animateDp(
        transitionSpec = { spring(dampingRatio = 1f, stiffness = 300f) },
        label = "",
        targetValueByState = { tabPositions[it].right }
    )

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .background(color = Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(50))
            .zIndex(-1f)
    )
}