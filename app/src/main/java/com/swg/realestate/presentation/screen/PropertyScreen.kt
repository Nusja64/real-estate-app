package com.swg.realestate.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.swg.realestate.domain.util.ResponseResult
import com.swg.realestate.domain.util.toMessage
import com.swg.realestate.presentation.model.PropertyUiModel
import com.swg.realestate.presentation.viewmodel.BookmarkViewModel
import com.swg.realestate.presentation.viewmodel.PropertyViewModel


@Composable
fun PropertyScreen(
    viewModel: PropertyViewModel,
    bookmarkViewModel: BookmarkViewModel
) {
    val propertyState = viewModel.propertyState.collectAsState().value
    val bookmarks = bookmarkViewModel.bookmarks.collectAsState().value
    val pullRefreshState = rememberPullToRefreshState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {

        when (propertyState) {

            is ResponseResult.Loading -> {
                LoadingScreen()
            }

            is ResponseResult.Error -> {
                val message = propertyState.error.toMessage()

                ErrorScreen(
                    message = message,
                    onRetry = { viewModel.getProperties() }
                )
            }

            is ResponseResult.Success -> {

                PullToRefreshBox(
                    isRefreshing = propertyState.data.isEmpty(),
                    state = pullRefreshState,
                    onRefresh = { viewModel.getProperties() }
                ) {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        items(
                            items = propertyState.data,
                            key = { it.id }
                        ) { item ->
                            val isBookmarked =
                                bookmarks.any { it.id == item.id }
                            val uiItem = item.copy(isBookmarked = isBookmarked)
                            ListingCard(
                                item = uiItem,
                                onBookmarkClick = {
                                    bookmarkViewModel.toggleBookmark(uiItem)
                                })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListingCard(
    item: PropertyUiModel,
    onBookmarkClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {

                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = item.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.address,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onBookmarkClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (item.isBookmarked)
                                android.R.drawable.btn_star_big_on
                            else
                                android.R.drawable.btn_star_big_off
                        ),
                        contentDescription = "bookmark",
                        tint = if (item.isBookmarked)
                            Color(0xFFFFC107)
                        else
                            Color.LightGray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ListingCardSimple(
    price: String,
    title: String,
    address: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Box {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(Color.LightGray)
                )

                Text(
                    text = price,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White, RoundedCornerShape(2.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = address,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListingsPreview_NoModel() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            ListingCardSimple(
                price = "538 €",
                title = "Erstbezug in TOP LAGE",
                address = "Singerstraße 33, Fhain"
            )
        }

        item {
            ListingCardSimple(
                price = "600 €",
                title = "Über den Dächern",
                address = "Finowstraße 34, XBerg"
            )
        }

        item {
            ListingCardSimple(
                price = "400 €",
                title = "Modernes Apartment",
                address = "Warschauer Str. 12, Berlin"
            )
        }
    }
}