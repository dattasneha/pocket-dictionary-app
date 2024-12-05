package com.snehadatta.pocketdictionary.feature_dictionary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snehadatta.pocketdictionary.feature_dictionary.presentation.WordInfoItem
import com.snehadatta.pocketdictionary.feature_dictionary.presentation.WordInfoViewModel
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Blue
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Green
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Orange
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Peach
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Shapes
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.PocketDictionaryTheme
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Purple200
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.Purple500
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.SkyBlue
import com.snehadatta.pocketdictionary.feature_dictionary.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketDictionaryTheme {
                val viewModel: WordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when(event) {
                            is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                androidx.compose.material.Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(
                        modifier = Modifier
                    ) {
                        Column(
                            modifier = Modifier.padding(top = 24.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Enter a word ✍️",
                                modifier = Modifier.padding(top = 24.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                                    .padding(horizontal = 16.dp)
                                    .clip(Shapes.large)
                                    .border(2.dp, Purple500, Shapes.large),
                                placeholder = {
                                    androidx.compose.material.Text(text = "Search...")
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Purple200
                                )
                            )


                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                items(state.wordInfoItems.size) { i ->
                                    val wordInfo = state.wordInfoItems[i]
                                    if(i > 0) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    WordInfoItem( wordInfo = wordInfo)
                                    if(i < state.wordInfoItems.size - 1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                        if(state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

            }
        }
    }
}