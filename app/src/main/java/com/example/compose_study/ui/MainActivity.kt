package com.example.compose_study.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.screen.RecyclerViewContent
import com.example.compose_study.ui.theme.Compose_studyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            Compose_studyTheme {
                MyApp(photoList = viewModel.photos)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(photoList: Flow<PagingData<Photo>>) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        RecyclerViewContent(photoList = photoList)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_studyTheme {
    }
}
