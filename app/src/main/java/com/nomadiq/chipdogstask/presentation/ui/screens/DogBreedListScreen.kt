package com.nomadiq.chipdogstask.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nomadiq.chipdogstask.R
import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.presentation.ui.component.DefaultSnackBarHost
import com.nomadiq.chipdogstask.presentation.ui.component.DogBreedItem
import com.nomadiq.chipdogstask.presentation.ui.component.DogBreedTopAppBar
import com.nomadiq.chipdogstask.presentation.ui.theme.FinNewsTheme
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListUiState

/**
 *  @author Michael Akakpo
 *
 *  Composable representing the list of [Dog breed] items within the Lazy column
 *
 */

@Composable
fun DogBreedListScreen(
    onItemClick: (DogBreed) -> Unit = {},
    uiState: DogBreedListUiState,
    navController: NavHostController,
) {
    FinNewsTheme {
        Scaffold(
            snackbarHost = { DefaultSnackBarHost(hostState = SnackbarHostState()) },
            topBar = {
                DogBreedTopAppBar(
                    title = stringResource(id = R.string.toolbar_title_dogbreed_list),
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { paddingValues ->
            val state = rememberLazyListState() //TODO - Pass state down and make stateful and stateless counterparts
            val items = uiState.items
            LazyColumn(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items.forEach { item ->
                    item {
                        DogBreedItem(item = item, onItemClick = onItemClick)
                    }
                }

                // Loading State
                item {
                    OnLoadingState(uiState)
                }

                // Check Internet
                item {
                    OnNetworkErrorState(uiState)
                }
            }
        }
    }
}

@Composable
fun OnLoadingState(uiState: DogBreedListUiState) {
    var loading by rememberSaveable { mutableStateOf(false) }
    loading = uiState.isLoading
    if (loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            CircularProgressIndicator()
            // LinearProgressIndicator()
        }
    }
}

@Composable
fun OnNetworkErrorState(uiState: DogBreedListUiState) {
    val list by rememberSaveable { mutableStateOf(uiState.errorMessage) }


    // Effect running in a coroutine that displays the Snackbar on the screen
    // If there's a change to errorMessageText, retryMessageText or snackbarHostState,
    // the previous effect will be cancelled and a new one will start with the new values
//    LaunchedEffect(snackbarHostState) {
//        val snackbarResult = snackbarHostState.showSnackbar(
//            message = "errorMessageText",
//            actionLabel = "retryMessageText"
//        )
//        if (snackbarResult == SnackbarResult.ActionPerformed) {
//         //   onRefreshPostsState()
//        }
//        // Once the message is displayed and dismissed, notify the ViewModel
//      //  onErrorDismissState(errorMessage.id)
//    }
}

@Composable
fun DisplayEmptyErrorState(uiState: DogBreedListUiState) {
    val list by rememberSaveable { mutableStateOf(uiState.items) }
}