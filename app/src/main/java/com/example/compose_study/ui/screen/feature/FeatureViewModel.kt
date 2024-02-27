package com.example.compose_study.ui.screen.feature

import com.example.compose_study.R
import com.example.compose_study.ui.BaseViewModel
import com.example.compose_study.utils.ui.Category
import com.example.compose_study.ui.screen.feature.component.QuickCardType
import com.example.compose_study.ui.screen.feature.component.QuickLink
import com.example.compose_study.ui.screen.feature.component.TopBanner
import com.example.compose_study.ui.screen.feature.data.getRandomTabs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
) : BaseViewModel() {

    private val _banners: MutableStateFlow<List<TopBanner>> = MutableStateFlow(emptyList())
    val banners: StateFlow<List<TopBanner>> = _banners.asStateFlow()

    private val _quickLists: MutableStateFlow<List<QuickLink>> = MutableStateFlow(emptyList())
    val quickLinks: StateFlow<List<QuickLink>> = _quickLists.asStateFlow()

    private val _quickCards: MutableStateFlow<List<QuickCardType>> = MutableStateFlow(emptyList())
    val quickCards: StateFlow<List<QuickCardType>> = _quickCards.asStateFlow()

    private val _tabs: MutableStateFlow<List<Category>> = MutableStateFlow<List<Category>>(emptyList())
    val tabs: StateFlow<List<Category>> = _tabs.asStateFlow()

    private val _selectedTabs: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val selectedTabs: StateFlow<Int> = _selectedTabs.asStateFlow()

    val isLoadingCompleted: StateFlow<Boolean> = combine(banners, quickLinks, quickCards) { banners, quickLinks, quickCards ->
        banners.isNotEmpty() && quickLinks.isNotEmpty() && quickCards.isNotEmpty()
    }.stateIn(
        baseViewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    init {
        baseViewModelScope.launch {
            loadReReservationTabs()
            delay(3000L)
            loadTopBanners()
            loadQuickLinks()
            loadQuickCards()
        }
    }

    private fun loadTopBanners() {
        val banner1 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/L9UdN/btsAJH5wAeB/EtTetfFHsKohELIwNsMlk1/img_750.jpg", title = "요즘 헤어 추구미\n일상에서도 유니크하게", description = "#처피뱅 #해쉬컷 #발레야쥬")
        val banner2 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/b7Z4VW/btsz6gGx68s/SOpIKTYerfjzdMCbg52Rvk/img_750.jpg", title = "요즘 헤어 추구미\n러블리 & 페미닌 무드 스타일", description = "#히피펌 #숏컷 #리프컷")
        val banner3 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/1paLT/btsyFoGNEqm/m7kIsrwvVCg7qHmKkVeLm1/img_750.jpg", title = "남자들의 워너비 헤어\n장발 추천 스타일 모음", description = "장발로 기를 때 하기 좋은 머리")
        val banner4 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/9iUwt/btsA20Q4Jq1/KjS7h8paKvYvBnTqFXMIkK/img_750.jpg", title = "비슷해 보여도 달라요!\n알고 쓰면 좋은 홈케어 사용법", description = "트린트먼트 vs 린스, 뭐가 다를까?")
        val banner5 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/b7t8zw/btsAmsOpz89/O7tu3eQUNLEkTyl0bQroIK/img_750.jpg", title = "나에게 딱 맞는\n가을 헤어 컬러는?", description = "피부 톤 찰떡 염색 컬러 추천")
        _banners.value = listOf(banner1, banner2, banner3, banner4, banner5)
    }

    private fun loadQuickLinks() {
        val hairQuickLink = QuickLink(title = "헤어샵", description = "컷/펌/염색\n스타일링\n메이크업", imageUri = R.mipmap.ic_quick_hair_foreground)
        val nailQuickLink = QuickLink(title = "네일샵", description = "케어\n네일", imageUri = R.mipmap.ic_quick_nail_foreground)
        val estheticQuickLink = QuickLink(title = "에스테틱", description = "왁싱\n브로우", imageUri = R.mipmap.ic_quick_esthetic_foreground)

        val subLink1 = QuickLink(title = "\uD83C\uDFE0 첫방문 기획전", description = "")
        val subLink2 = QuickLink(title = "\uD83C\uDFC6 어워즈", description = "")
        val subLink3 = QuickLink(title = "⭐️ 리뷰별점높은샵", description = "")
        val subLink4 = QuickLink(title = "\uD83D\uDC87 스타일TIP", description = "")

        _quickLists.value = listOf<QuickLink>(hairQuickLink, nailQuickLink, estheticQuickLink, subLink1, subLink2, subLink3, subLink4, subLink1, subLink2, subLink3, subLink4)
    }

    private fun loadQuickCards() {
        _quickCards.value = listOf<QuickCardType>(
            QuickCardType.WELCOME,
            QuickCardType.RESERVATION,
            QuickCardType.D_DAY,
            QuickCardType.BEFORE_REVIEW,
            QuickCardType.AFTER_REVIEW,
            QuickCardType.RE_RESERVATION_FEMALE,
            QuickCardType.RE_RESERVATION_MALE,
            QuickCardType.MY_DESIGNER,
            QuickCardType.MY_MENU,
            QuickCardType.NORMAL
        )
    }

    fun loadReReservationTabs() {
        baseViewModelScope.launch {
            _selectedTabs.value = 0
            _tabs.value = getRandomTabs()
        }
    }

    fun onSelectedTab(tabIndex: Int) = baseViewModelScope.launch {
        _selectedTabs.value = tabIndex
    }
}
