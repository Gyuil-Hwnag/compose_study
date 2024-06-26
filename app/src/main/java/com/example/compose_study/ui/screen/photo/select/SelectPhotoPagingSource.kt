package com.example.compose_study.ui.screen.photo.select

import android.net.Uri
import android.provider.MediaStore
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.compose_study.utils.ImageStorage


const val ALL_ALBUM_BUCKET_ID = Long.MIN_VALUE

fun createSelectPhotoPager(
    mediaBucketId: Long = ALL_ALBUM_BUCKET_ID,
    imageStorage: ImageStorage
): Pager<Int, SelectPhoto> = Pager(
    config = PagingConfig(pageSize = 21, initialLoadSize = 21, enablePlaceholders = true),
    initialKey = 0,
    pagingSourceFactory = { SelectPhotoPagingSource(mediaBucketId, imageStorage) }
)

class SelectPhotoPagingSource(
    private val imageBucketId: Long = ALL_ALBUM_BUCKET_ID,
    private val imageStorage: ImageStorage
) : PagingSource<Int, SelectPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, SelectPhoto>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SelectPhoto> {
        val currentOffset = params.key ?: 0

        val result = runCatching {
            imageStorage.getImageItems(
                mediaBucketId = imageBucketId,
                offset = currentOffset,
                limit = params.loadSize
            )
        }

        return result.fold(
            onSuccess = { contents ->
                LoadResult.Page(
                    data = contents.content.map { model ->
                        SelectPhoto(
                            id = model.imageId,
                            imageUrl = Uri.withAppendedPath(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                model.imageId.toString()
                            ).toString()
                        )
                    },
                    prevKey = null,
                    nextKey = if (contents.hasNext == true) currentOffset + params.loadSize else null
                )
            },
            onFailure = { e -> LoadResult.Error(e) }
        )
    }
}
