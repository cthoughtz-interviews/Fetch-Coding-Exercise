package com.simple.fetchrewardcompose.presentation.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.simple.fetchrewardcompose.presentation.viewmodel.ItemsViewModel
import com.simple.fetchrewardcompose.presentation.views.pages.MainPage
import com.simple.fetchrewardcompose.ui.theme.FetchRewardComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchRewardComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel = hiltViewModel<ItemsViewModel>()
                    MainPage(viewModel)
                }
            }
        }
    }
}