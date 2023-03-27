package com.example.compose_study.ui.screen.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.IconUtils

@Composable
fun MoreScreen(
    onClick: (More) -> Unit
) {
    val moreItems = listOf(More.Profile, More.Notification, More.Information, More.Question)
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                items = moreItems,
                itemContent = { MoreItem(item = it, onClick = { more -> onClick(more) } ) }
            )
        }
    }
}

@Composable
fun MoreItem(item: More, onClick: (more: More) -> Unit) {
    val imgSrc = when(item) {
        is More.Profile -> LocalContext.current.getDrawable(com.example.compose_study.R.drawable.ic_profile)
        is More.Notification -> LocalContext.current.getDrawable(com.example.compose_study.R.drawable.ic_notification)
        is More.Information -> LocalContext.current.getDrawable(com.example.compose_study.R.drawable.ic_information)
        is More.Question -> LocalContext.current.getDrawable(com.example.compose_study.R.drawable.ic_question)
    }
    val textSrc = when(item) {
        is More.Profile -> LocalContext.current.getString(com.example.compose_study.R.string.title_profile)
        is More.Notification -> LocalContext.current.getString(com.example.compose_study.R.string.title_notification)
        is More.Information -> LocalContext.current.getString(com.example.compose_study.R.string.title_information)
        is More.Question -> LocalContext.current.getString(com.example.compose_study.R.string.title_question)
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick.invoke(item) }
    ) {
        IconUtils(url = imgSrc!!)
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 4.dp, vertical = 16.dp)
                .align(Alignment.CenterVertically),
            text = textSrc
        )
        IconUtils(url = LocalContext.current.getDrawable(com.example.compose_study.R.drawable.ic_next)!!)
    }
}

sealed class More {
    object Profile: More()
    object Notification: More()
    object Information: More()
    object Question: More()
}
