package com.plcoding.composenavdestinationsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.composenavdestinationsdemo.destinations.PostScreenDestination
import com.plcoding.composenavdestinationsdemo.destinations.ProfileScreenDestination
import com.plcoding.composenavdestinationsdemo.ui.theme.ComposeNavDestinationsDemoTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavDestinationsDemoTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navigator.navigate(
                ProfileScreenDestination(
                    User(
                        name = "Chris P. Bacon",
                        id = "userid",
                        created = LocalDateTime.now()
                    )
                )
            )
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    user: User
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navigator.navigate(PostScreenDestination())
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Destination
@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}