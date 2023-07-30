package org.marshsoft.jobsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.marshsoft.jobsearch.ui.theme.JobSearchTheme
import org.marshsoft.jobsearch.views.HomeScreen
import org.marshsoft.jobsearch.views.JobDetailsScreen
import org.marshsoft.jobsearch.views.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JobSearchTheme {
        Greeting("Android")
    }
}
@Composable
fun NavHostContainer(navController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
    ){
        composable("home") {
            HomeScreen(navController)
        }
        composable("bookmark") {

        }
        composable("messages") {

        }
        composable("profile") {

        }
        composable("jobDetails/{jobId}",arguments = listOf(navArgument("jobId") {
            type = NavType.StringType
        })){
            val jobId = it.arguments?.getString("jobId")
            jobId?.let {
                JobDetailsScreen(jobId = jobId )

            }

        }


    }

}