package com.example.compose_study.ui.screen.dialog

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScreen(
    modifier: Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var isSheetFullScreen by remember { mutableStateOf(false) }
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val modifier = if (isSheetFullScreen) modifier.fillMaxSize() else modifier.fillMaxWidth()

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
            Column(
                modifier = modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        isSheetFullScreen = !isSheetFullScreen
                    }
                ) {
                    Text(text = "Toggle Sheet Fullscreen")
                }

                Button(
                    onClick = {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }
            }
        }
    ) {
        Scaffold { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                val context = LocalContext.current
                var showDialog by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(onClick = { showDialog = true }) {
                        Text(text = "Show Dialog")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                if (modalSheetState.isVisible) modalSheetState.hide()
                                else modalSheetState.show()
                            }
                        },
                    ) {
                        Text(text = "Show Bottom Sheet")
                    }
                }

                if(showDialog) {
                    CustomDialogScreen(
                        onDismiss = {
                            showDialog = false
                            Toast.makeText(context, "Dismiss Dialog", Toast.LENGTH_SHORT).show()
                        },
                        onNegativeClick = {
                            showDialog = false
                            Toast.makeText(context, "Negative Dialog", Toast.LENGTH_SHORT).show()
                        },
                        onPositiveClick = {
                            showDialog = false
                            Toast.makeText(context, "Positive Dialog", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.padding(contentPadding)
                    )
                }
            }
        }
    }
}
