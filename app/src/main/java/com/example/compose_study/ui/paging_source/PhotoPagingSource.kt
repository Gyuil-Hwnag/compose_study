package com.example.compose_study.ui.paging_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.compose_study.domain.GetPhotoListUseCase
import com.example.compose_study.model.Photo

fun createPhotoPager(
    getPhotoListUseCase: GetPhotoListUseCase
): Pager<Int, Photo> = Pager(
    config = PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = true),
    initialKey = 0,
    pagingSourceFactory = {
        PostsPagingSource(
            getPhotoListUseCase = getPhotoListUseCase
        )
    }
)

class PostsPagingSource(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val pageIndex = params.key ?: 0
        val result = getPhotoListUseCase.invoke(
            page = pageIndex,
            limit = PAGE_SIZE
        )
        return LoadResult.Page(
            data = result,
            prevKey = null,
            nextKey = if(result.isNotEmpty()) pageIndex+1 else null
        )
    }
}

const val PAGE_SIZE = 20
