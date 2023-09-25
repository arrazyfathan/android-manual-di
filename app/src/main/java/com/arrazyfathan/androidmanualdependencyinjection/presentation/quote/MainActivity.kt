package com.arrazyfathan.androidmanualdependencyinjection.presentation.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arrazyfathan.androidmanualdependencyinjection.App
import com.arrazyfathan.androidmanualdependencyinjection.presentation.ui.theme.AndroidManualDependencyInjectionTheme
import com.arrazyfathan.androidmanualdependencyinjection.utils.viewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidManualDependencyInjectionTheme {
                val viewModel = viewModel<QuoteViewModel>(
                    factory = viewModelFactory {
                        QuoteViewModel(App.appModule.repository)
                    }
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.quoteUiState.collectAsState()
                    Box {
                        when (state) {
                            QuoteUiState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center),
                                )
                            }

                            is QuoteUiState.Success -> {
                                (state as QuoteUiState.Success).quote?.let { quote ->
                                    Text(text = quote.content, modifier = Modifier.align(Alignment.Center))
                                }
                            }

                            is QuoteUiState.Failed -> {
                                (state as QuoteUiState.Failed).message?.let { error ->
                                    Text(text = error, modifier = Modifier.align(Alignment.Center))
                                }
                            }
                        }

                        Button(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp),
                            onClick = viewModel::stopRepeating
                        ) {
                            Text("Stop Update Quote")
                        }
                    }

                }
            }
        }
    }
}