package com.example.compose_study.ui.screen.feature

import com.example.compose_study.R
import com.example.compose_study.ui.BaseViewModel
import com.example.compose_study.ui.screen.feature.component.QuickCardType
import com.example.compose_study.ui.screen.feature.component.QuickLink
import com.example.compose_study.ui.screen.feature.component.TopBanner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    init {
        loadTopBanners()
        loadQuickLinks()
        loadQuickCards()
    }

    fun loadTopBanners() {
        val banner1 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/blIWAg/btsrNmiPTFm/Mg2IrGwlU7k00p2EzZxH4K/img_750.jpg", title = "요즘 헤어 추구미\n일상에서도 유니크하게", description = "유니크한 헤어 스타일")
        val banner2 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/1paLT/btsyFoGNEqm/m7kIsrwvVCg7qHmKkVeLm1/img_750.jpg", title = "남자들의 워너비 헤어\n장발 추천 스타일 모음", description = "남자들의 워너비 장발 스타일")
        val banner3 = TopBanner(imageUri = "https://mud-kage.kakao.com/dn/9iUwt/btsA20Q4Jq1/KjS7h8paKvYvBnTqFXMIkK/img_750.jpg", title = "비슷해 보여도 달라요!\n알고 쓰면 좋은 홈케어 사용법", description = "홈케어 사용방법")
        _banners.value = listOf(banner1, banner2, banner3, banner1, banner2, banner3)
    }

    fun loadQuickLinks() {
        val hairQuickLink = QuickLink(title = "헤어샵", description = "컷/펌/염색\n스타일링\n메이크업", imageUri = R.mipmap.ic_quick_hair_foreground)
        val nailQuickLink = QuickLink(title = "네일샵", description = "케어\n네일", imageUri = R.mipmap.ic_quick_nail_foreground)
        val estheticQuickLink = QuickLink(title = "에스테틱", description = "왁싱\n브로우", imageUri = R.mipmap.ic_quick_esthetic_foreground)

        val subLink1 = QuickLink(title = "\uD83C\uDFE0 첫방문 기획전", description = "")
        val subLink2 = QuickLink(title = "\uD83C\uDFC6 어워즈", description = "")
        val subLink3 = QuickLink(title = "⭐️ 리뷰별점높은샵", description = "")
        val subLink4 = QuickLink(title = "\uD83D\uDC87 스타일TIP", description = "")
        _quickLists.value = listOf<QuickLink>(hairQuickLink, nailQuickLink, estheticQuickLink, subLink1, subLink2, subLink3, subLink4, subLink1, subLink2, subLink3, subLink4)
    }

    fun loadQuickCards() {
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
}
