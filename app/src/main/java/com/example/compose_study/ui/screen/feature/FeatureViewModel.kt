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
        val banner1 = TopBanner(imageUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToWctFQXH8UDrKh45hZk7toE2r-Yocs7ys6g&usqp=CAU", title = "헤어 스타일의 모든것\nBOBBED HAIR STYLE", description = "요즘 유행하는 스타일")
        val banner2 = TopBanner(imageUri = "https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg", title = "네일 스타일의 모든것\nBOBBED NAIL STYLE", description = "요즘 유행하는 네일 스타일")
        val banner3 = TopBanner(imageUri = "https://mblogthumb-phinf.pstatic.net/MjAxODA2MjZfMTMw/MDAxNTMwMDE0NDk2Mzcz.7re42MA5wqJxZlJ8J5FzfDKEEqugtVuhg49bSFYUuYsg.0Y0kjwH4oi1LXXpqrcGaVBch_4eQsyKyVTRsNtg7fCMg.JPEG.ichufs/%EC%82%AC%EC%A7%84%EC%8C%A4%EC%9A%B0%EC%93%B0%EB%9D%BC_3_0%EC%9D%B8%ED%8A%B8%EB%A1%9C.jpg?type=w800", title = "에스테틱의 모든것\nBOBBED ESTHETIC STYLE", description = "요즘 유행하는 에스테틱 스타일")
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
