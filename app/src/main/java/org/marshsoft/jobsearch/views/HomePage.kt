package org.marshsoft.jobsearch.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.marshsoft.jobsearch.NavHostContainer
import org.marshsoft.jobsearch.R
import org.marshsoft.jobsearch.entities.Job
import org.marshsoft.jobsearch.ui.theme.CardPurple
import org.marshsoft.jobsearch.ui.theme.Purple40

@Composable
    fun JobCardItem(job: Job){
        Column(modifier = Modifier.background(CardPurple)) {
            Row() {

            }

        }
    }
    fun JobCardList(){

    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(){
        TopAppBar(
            title = {
                Row() {
                    Text("Hii Jay")
                    Spacer(modifier = Modifier.weight(1f,true))
                    Image(painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "person",
                        modifier = Modifier
                            .width(59.dp)
                            .height(59.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                            .shadow(2.dp, CircleShape))
                }},
            navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(R.drawable.vectorburger), contentDescription = "burger icon")
            }},
            modifier = Modifier.background(color = Color.White)

        )

    }
    @Composable
    fun BottomNavigationBar(navController:NavHostController){
        val items = listOf(
            BottomNavigationScreens.Home,
            BottomNavigationScreens.BookMark,
            BottomNavigationScreens.Messages,
            BottomNavigationScreens.Profile
        )
        NavigationBar(
           modifier = Modifier
               .fillMaxWidth(),
            containerColor = Color.White
        )
        {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach{navItem->
                NavigationBarItem(
                    selected = currentRoute == navItem.screenRoute,
                    onClick = { navController.navigate(navItem.screenRoute) },
                    icon = {
                        Icon(
                            painter = painterResource(id = navItem.icon),
                            contentDescription = navItem.title
                        )
                    },
                    label = { Text(text = navItem.title) },
                    alwaysShowLabel = false
                )


            }
        }
    }
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        content = {innerPadding->NavHostContainer(navController = navController, padding = innerPadding)},
        bottomBar = {BottomNavigationBar(navController = navController)}

    )

}
@Composable
fun HomeScreen(){
    var value by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState())) {
        Text("Your skill is required for many  jobs")
        Image(painter = painterResource(id = R.drawable.rectangle_1homepage), contentDescription ="Image of people interviewing" )
        TextField(value = value, onValueChange = {newText->value = newText} )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Based on your skills", color = Purple40)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                Text("View All")
            }

        }
        JobCardList()

    }
}



