package com.swg.realestate.domain.repository

import com.swg.realestate.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    fun getBookmarks(): Flow<List<BookmarkEntity>>

    fun isBookmarked(id: String): Flow<Boolean>

    suspend fun toggle(bookmark: BookmarkEntity)
}