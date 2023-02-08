package com.example.compose_study.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import com.example.compose_study.model.Photo
import com.example.compose_study.ui.screen.RecyclerViewContent
import com.example.compose_study.ui.screen.TextFieldItem
import com.example.compose_study.ui.theme.Compose_studyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            Compose_studyTheme {
                MyApp(viewModel = viewModel)

                lifecycleScope.launchWhenStarted {
                    viewModel.searchState.collectLatest {
                        Toast.makeText(applicationContext, viewModel.searchState.value, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(viewModel: MainViewModel) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.fillMaxWidth().weight(8f)) {
                RecyclerViewContent(photoList = viewModel.photos)
            }
            TextFieldItem(viewModel)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_studyTheme {
    }
}
