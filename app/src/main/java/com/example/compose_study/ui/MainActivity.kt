package com.example.compose_study.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose_study.ui.screen.main.BottomNavigationScreen
import com.example.compose_study.ui.theme.ComposeStudyTheme
import com.example.compose_study.utils.notification.PushMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                MainScreenView()
            }
        }
    }

    companion object {

        private const val EXTRA_PUSH_MESSAGE = "PUSH_MESSAGE"

        fun starterIntent(
            context: Context,
            pushMessage: PushMessage? = null
        ): Intent {
            return Intent(context, MainActivity::class.java).apply {
                if (!pushMessage?.uri.isNullOrBlank()) data = Uri.parse(pushMessage?.uri)
                if (pushMessage != null) putExtra(EXTRA_PUSH_MESSAGE, pushMessage)
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationScreen(navController = navController) }
    ) { contentPadding ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NavigationGraph(
                navController = navController,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStudyTheme {
    }
}
