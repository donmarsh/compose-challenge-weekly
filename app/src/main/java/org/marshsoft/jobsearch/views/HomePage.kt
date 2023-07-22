package org.marshsoft.jobsearch.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.marshsoft.jobsearch.NavHostContainer
import org.marshsoft.jobsearch.entities.Job
import org.marshsoft.jobsearch.ui.theme.CardPurple

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
            title = {Text("Hii Jay")},
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
        val bottomNavigationItems = listOf(
            BottomNavigationScreens.Home,
            BottomNavigationScreens.BookMark,
            BottomNavigationScreens.Messages,
            BottomNavigationScreens.Profile
        )
        Scaffold(
            topBar = { TopBar() },
            content = {innerPadding->NavHostContainer(navController = navController, padding = innerPadding)},
            bottomBar = {BottomNavigationBar(navController = navController)}

        )

    }


