package com.example.jetpackcomposemvvvm.iu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposemvvvm.iu.ShowLoadingView
import com.example.jetpackcomposemvvvm.pojo.Post
import com.example.jetpackcomposemvvvm.pojo.ViewState
import com.example.jetpackcomposemvvvm.ui.theme.JetpackComposeMvvvmTheme
import com.example.jetpackcomposemvvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetpackComposeMvvvmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    GetPostsData(mainViewModel = mainViewModel)
                }
            }
        }
    }
}

@Composable
fun GetPostsData(mainViewModel: MainViewModel) {
    // use launch effect to avoid recomposition of the viewModel method
    LaunchedEffect(key1 = true) {
        mainViewModel.getPostList()
    }
    when (val result = mainViewModel.postResponse.value) {
        is ViewState.Success -> {
            RecycleView(result.data)
        }
        is ViewState.Loading -> {
            ShowLoadingView()
        }
        is ViewState.Error -> {
        }
        is ViewState.NetworkError -> {
        }
        is ViewState.Ideal -> {
            
        }
    }
}

@Composable
fun EachRow(post: Post) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = post.first_name,
            modifier = Modifier.padding(10.dp),
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun RecycleView(data: List<Post>) {
    LazyColumn {
        items(data) {
            EachRow(post = it)
        }
    }

}

/*@Composable
fun ShowLoadingView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeMvvvmTheme {
//        Greeting("Android")
    }
}