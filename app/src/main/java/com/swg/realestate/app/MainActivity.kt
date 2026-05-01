package com.swg.realestate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.swg.realestate.data.local.db.AppDatabase
import com.swg.realestate.data.remote.RetrofitClient
import com.swg.realestate.data.repository.BookmarkRepositoryImpl
import com.swg.realestate.data.repository.PropertyRepositoryImpl
import com.swg.realestate.presentation.screen.PropertyScreen
import com.swg.realestate.presentation.viewmodel.BookmarkViewModel
import com.swg.realestate.presentation.viewmodel.PropertyViewModel
import com.swg.realestate.ui.theme.SWGRealEstateTheme

class MainActivity : ComponentActivity() {

    private val database by lazy {
        AppDatabase.getDatabase(applicationContext)
    }

    private val bookmarkRepository by lazy {
        BookmarkRepositoryImpl(database.bookmarkDao())
    }

    private val propertyViewModel by lazy {
        PropertyViewModel(
            PropertyRepositoryImpl(RetrofitClient.api)
        )
    }

    private val bookmarkViewModel by lazy {
        BookmarkViewModel(bookmarkRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SWGRealEstateTheme {
                PropertyScreen(
                    viewModel = propertyViewModel,
                    bookmarkViewModel = bookmarkViewModel
                )
            }
        }
    }
}
