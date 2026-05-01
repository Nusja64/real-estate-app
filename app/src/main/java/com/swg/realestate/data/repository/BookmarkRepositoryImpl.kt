package com.swg.realestate.data.repository

import com.swg.realestate.data.local.dao.BookmarkDao
import com.swg.realestate.data.local.entity.BookmarkEntity
import com.swg.realestate.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.first

class BookmarkRepositoryImpl(
    private val dao: BookmarkDao
) : BookmarkRepository {

    override fun getBookmarks() = dao.getBookmarks()

    override fun isBookmarked(id: String) = dao.isBookmarked(id)

    override suspend fun toggle(bookmark: BookmarkEntity) {

        val exists = dao.isBookmarked(bookmark.id)
            .first()

        if (exists) {
            dao.deleteById(bookmark.id)
        } else {
            dao.insert(bookmark)
        }
    }
}
