package org.marshsoft.jobsearch.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.HourglassFull
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.marshsoft.jobsearch.NavHostContainer
import org.marshsoft.jobsearch.R
import org.marshsoft.jobsearch.entities.Job
import org.marshsoft.jobsearch.ui.theme.BlackText
import org.marshsoft.jobsearch.ui.theme.CardPurple
import org.marshsoft.jobsearch.ui.theme.GreyText
import org.marshsoft.jobsearch.ui.theme.MainPurple

@Composable
fun JobCardItem(job: Job){
    var favorite by remember {
        mutableStateOf(false) // initially checked
    }
    Box(modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .background(CardPurple)
        .width(IntrinsicSize.Max)
        .padding(5.dp))
    {
        Column(modifier = Modifier.width(IntrinsicSize.Max))
        {
            Row(modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(job.imageUrl)
                        .build(),
                    modifier = Modifier
                        .height(62.dp)
                        .width(62.dp)
                        .padding(bottom = 5.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Job Icon Image",
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(id = R.drawable.vectorstar),
                        contentDescription = "star",
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp).padding(end = 2.dp))
                    Text(job.rating, modifier = Modifier
                                    .height(24.dp).
                                    width(24.dp).
                        wrapContentHeight(align = Alignment.CenterVertically),
                        style = TextStyle(fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = GreyText))
                }


            }
            Text(job.jobTitle, style = TextStyle(color = BlackText, fontSize = 16.sp), modifier = Modifier.padding(top = 3.dp))
            Text(job.location, style = TextStyle(color = GreyText), modifier = Modifier.padding(top = 3.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                TextWithIcon(imageVector = Icons.Outlined.Schedule, text = job.jobPostDate, color = GreyText)
                TextWithIcon(imageVector = Icons.Filled.HourglassFull , text = job.jobType, color = GreyText)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = MainPurple, contentColor = Color.White)) {
                    Text("Apply now")
                }
                IconToggleButton(checked = favorite, onCheckedChange = {_favorite->favorite = _favorite})

                {
                    Icon(imageVector = if(favorite)Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Bookmark icon",
                        tint = MainPurple)

                }
            }

                
            }


        }


}
@Composable
fun TextWithIcon(imageVector:ImageVector, text:String, color: Color)
{
    val dividerId = "inlineDividerId"
    val textString = buildAnnotatedString {
        appendInlineContent(dividerId,"[divider]")
        append(text)
    }
    val inlineIconText = mapOf(
        Pair(
            dividerId,
            InlineTextContent(
                Placeholder(
                    width = 14.sp,
                    height = 14.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ){
                Icon(imageVector = imageVector,"", tint = color)
            }
        )
    )
    Text(text = textString, inlineContent = inlineIconText, style = TextStyle(color = color), modifier = Modifier.padding(top = 3.dp))

}
@Composable
fun JobCardList(jobs:List<Job>){
    LazyRow(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(jobs){job->
            JobCardItem(job = job)

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    val context = LocalContext.current
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Hii Jay", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Image(painter = painterResource(id = R.drawable.kurt_resize),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "person",
                    modifier = Modifier
                        .width(59.dp)
                        .height(59.dp)
                        .clip(CircleShape)
                        .background(color = Color.Transparent)
                        .border(5.dp, Color.White, CircleShape)
                        .shadow(2.dp, CircleShape)

                        )


            }},
        navigationIcon = { IconButton(onClick = { Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show() }) {
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
        .padding(10.dp)
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Your skill is required for many  jobs",style = TextStyle(
            fontSize = 28.sp,
            lineHeight = 38.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF1A1A1A)
        ) )
        Image(painter = painterResource(id = R.drawable.rectangle_1homepage),
            contentDescription ="Image of people interviewing",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillWidth)
        TextField(value = value, colors = TextFieldDefaults.colors(
            cursorColor = Color.White,
            focusedContainerColor = MainPurple,
            unfocusedContainerColor = MainPurple,
            focusedTextColor = Color.White,
            unfocusedPlaceholderColor = Color.White,
            focusedPlaceholderColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

        ),
            onValueChange = {newText->value = newText},
            modifier = Modifier
                .offset(y = (-20).dp)
                .clip(RoundedCornerShape(20.dp))
                        ,
            placeholder = {Text("Search any Job...")},
            leadingIcon = {Icon(imageVector = Icons.Default.Search, tint = Color.White, contentDescription = null)}
        )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Based on your skills", color = Color.Black)
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = MainPurple)) {
                Text("View All")
            }

        }
        val firstJob = Job(id = 1,
            location = "Gurugram, Haryana",
            jobTitle = "Swift Developer",
            companyDetails = "Company Details", jobType = "Full Time", description = "description",
            jobPostDate = "2 days ago", rating = "4.5", imageUrl = "https://res.cloudinary.com/ds8ursyfi/image/upload/v1690368712/jobs/swift.png")
        val secondJob = Job(id = 2,
            location = "Delhi, New Delhi",
            jobTitle = "C Sharp Developer",
            companyDetails = "Company Details", jobType = "Full Time", description = "description",
            jobPostDate = "2 days ago", rating = "4.4", imageUrl = "https://res.cloudinary.com/ds8ursyfi/image/upload/v1690368732/jobs/Group_1286csharpicon.png")
        JobCardList(arrayListOf(firstJob, secondJob))

    }
}



