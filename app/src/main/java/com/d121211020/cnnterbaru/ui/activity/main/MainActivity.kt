package com.d121211020.cnnterbaru.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.d121211020.cnnterbaru.data.model.Data
import com.d121211020.cnnterbaru.ui.theme.CnnTerbaruTheme
import com.d121211020.cnnterbaru.ui.activity.detail.DetailActivity

import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter





@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CnnTerbaruTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TopAppBar(
                            title = { Text(text = "CNN Terbaru") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.background
                            ),
                        )

                        val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListDataScreen(mainViewModel.mainUiState)
                    }
                }
            }
        }
    }

    @Composable
    private fun ListDataScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        Column {
            when (mainUiState) {
                is MainUiState.Success -> ListData(mainUiState.data)
                is MainUiState.Error -> ErrorText()
                is MainUiState.Loading -> LoadingBar()
            }
        }
    }


    @Composable
    private fun ErrorText() {
        Text(text = "ERROR")
    }

    @Composable
    fun LoadingBar() {
        Text(text = "LOADING")
    }

    @Composable
    private fun ListData(data: Data, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(data) { data ->
                DataCard(data = data)
            }
        }
    }

    @Composable
    private fun DataCard(data: Data, modifier: Modifier = Modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("DATA", data)
                    startActivity(intent)
                },
            colors = CardDefaults.cardColors(
                containerColor = Color.White),
            shape = MaterialTheme.shapes.medium,
//            elevation = 4.dp
        ) {
            Column {
                // Load thumbnail using CoilImage
//                CoilImage(
//                    data = data.image,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .height(200.dp)
//                        .fillMaxWidth()
//                        .clip(shape = MaterialTheme.shapes.medium),
//                    contentScale = ContentScale.Crop
//                )
                DataThumbnail(data.image)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = data.title ?: "Loading Title",
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                    )
                )

                Text(
                    text = data.description ?: "Loading Description",
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(bottom = 16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
    @Composable
    fun DataThumbnail(imageUrl: String) {
        val painter: Painter = rememberAsyncImagePainter(imageUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
    }
}
