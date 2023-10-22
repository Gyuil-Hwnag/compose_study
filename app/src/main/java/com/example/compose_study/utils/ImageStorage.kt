package com.example.compose_study.utils

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.os.Build
import android.provider.MediaStore
import androidx.core.os.bundleOf
import javax.inject.Inject

private typealias BucketName = String
private typealias BucketId = Long

class ImageStorage @Inject constructor(
    private val context: Context
) {

    private val resolver: ContentResolver
        get() = context.contentResolver

    companion object {

        private const val ALL_ALBUM_BUCKET_ID = Long.MIN_VALUE

        private const val BUCKET_ID = MediaStore.Images.Media.BUCKET_ID
        private const val BUCKET_DISPLAY_NAME = MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        private const val _ID = MediaStore.Images.Media._ID
        private const val SIZE = MediaStore.Images.Media.SIZE

        private val EXTERNAL_CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        private const val DATE_MODIFIED = MediaStore.Images.Media.DATE_MODIFIED
    }

    fun getImageBuckets(): List<ImageBucket> =
        resolver.query(
            EXTERNAL_CONTENT_URI,
            arrayOf(BUCKET_ID, BUCKET_DISPLAY_NAME),
            null,
            null,
            "$BUCKET_DISPLAY_NAME COLLATE NOCASE ASC"
        )?.use { bucketCursor ->
            return@use generateSequence { if (bucketCursor.moveToNext()) bucketCursor else null }
                .map { it.toBucketNameAndId() }
                .filterNotNull()
                .groupBy { (name, id) -> name to id }
                .map { (nameAndId, list) -> ImageBucket(name = nameAndId.first, id = nameAndId.second, count = list.size) }
                .toList()
        } ?: throw NullPointerException("cursor is null")


    fun getImageItems(mediaBucketId: Long, offset: Int, limit: Int): Contents<ImageItem> {
        createImageCursor(mediaBucketId, offset, limit)
            ?.use { imageCursor ->
                val sizeIndex = imageCursor.getColumnIndex(SIZE)
                return generateSequence { if (imageCursor.moveToNext()) imageCursor else null }
                    .filter { cursor -> cursor.getLong(sizeIndex) > 0 }
                    .map { it.toImageItem() }
                    .filterNotNullTo(mutableListOf())
                    .let { imageItems ->
                        Contents(
                            content = imageItems,
                            hasNext = imageItems.isNotEmpty(),
                            totalCount = 0
                        )
                    }
            } ?: throw NullPointerException("cursor is null")
    }

    private fun createImageCursor(mediaBucketId: Long, offset: Int, limit: Int): Cursor? {
        val isNotAllBucketId = mediaBucketId != ALL_ALBUM_BUCKET_ID
        val url = EXTERNAL_CONTENT_URI
        val projection = arrayOf(_ID, SIZE)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val queryArgs = listOf(
                ContentResolver.QUERY_ARG_LIMIT to limit,
                ContentResolver.QUERY_ARG_OFFSET to offset,
                ContentResolver.QUERY_ARG_SQL_SORT_ORDER to "$DATE_MODIFIED desc "
            )
            val bucketQueryArgs = if (isNotAllBucketId) listOf(
                ContentResolver.QUERY_ARG_SQL_SELECTION to "$BUCKET_ID=?",
                ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS to arrayOf(mediaBucketId.toString())
            ) else emptyList()
            resolver.query(url, projection, bundleOf(*(queryArgs + bucketQueryArgs).toTypedArray()), null)
        } else {
            val selection = if (isNotAllBucketId) "$BUCKET_ID=?" else null
            val selectionArgs = if (isNotAllBucketId) arrayOf(mediaBucketId.toString()) else null
            resolver.query(
                url.buildUpon().encodedQuery("limit=$offset,$limit").build(),
                projection,
                selection,
                selectionArgs,
                "$DATE_MODIFIED desc "
            )
        }
    }

    private fun Cursor.toBucketNameAndId(): Pair<BucketName, BucketId> {
        val bucketIdIndex = getColumnIndex(BUCKET_ID)
        val bucketNameIndex = getColumnIndex(BUCKET_DISPLAY_NAME)

        val bucketName = getString(bucketNameIndex).takeIf { it.isNullOrBlank().not() } ?: "unknown"
        val bucketId = getLong(bucketIdIndex)
        return bucketName to bucketId
    }

    private fun Cursor.toImageItem(): ImageItem {
        val imageIdIndex = getColumnIndex(_ID)
        val imageId = getLong(imageIdIndex)
        val imagePath = ContentUris.withAppendedId(EXTERNAL_CONTENT_URI, imageId)
        return ImageItem(imagePath.toString(), imageId)
    }

}
