package com.swg.realestate.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swg.realestate.data.local.entity.BookmarkEntity
import com.swg.realestate.domain.repository.BookmarkRepository
import com.swg.realestate.presentation.model.PropertyUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class BookmarkViewModel(
    private val repo: BookmarkRepository
) : ViewModel() {

    val bookmarks = repo.getBookmarks()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun toggleBookmark(item: PropertyUiModel) {

        viewModelScope.launch {

            val entity = BookmarkEntity(
                id = item.id,
                title = item.title,
                price = item.price,
                address = item.address,
                imageUrl = item.imageUrl
            )

            repo.toggle(entity)
            Log.d("BOOKMARK_DEBUG", "Toggled: ${entity.id}")
        }
    }
}