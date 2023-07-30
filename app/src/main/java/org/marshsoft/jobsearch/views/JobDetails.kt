package org.marshsoft.jobsearch.views

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.marshsoft.jobsearch.NavHostContainer
import org.marshsoft.jobsearch.R
import org.marshsoft.jobsearch.entities.Job
import org.marshsoft.jobsearch.ui.theme.BlackText
import org.marshsoft.jobsearch.ui.theme.MainPurple
import org.marshsoft.jobsearch.ui.theme.progressBackGround

@Composable
fun JobDetails(job:Job, navController:NavHostController)
{
    Scaffold(
        topBar = { TopBarJobDetails(job) },
        content = {innerPadding-> NavHostContainer(navController = navController, padding = innerPadding) },
        bottomBar = {BottomNavigationBar(navController = navController)}

    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarJobDetails(job:Job){
    val context = LocalContext.current
    var favorite by remember {
        mutableStateOf(job.favorite) // initially checked
    }
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text("InfoSys", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            IconToggleButton(checked = favorite, onCheckedChange = { favorite = it})
            {
                Icon(imageVector = if(favorite) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "Bookmark icon",
                    tint = Color.Black
                )

            }


        }
    },
        navigationIcon = { IconButton(onClick = { Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show() }) {
            Icon(painter = painterResource(R.drawable.vectorburger), contentDescription = "burger icon")
        }},
        modifier = Modifier.background(color = Color.White))
}
@Composable
fun CustomProgressBar(){
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(6.dp)){
        val canvasWidth = size.width
        val canvasHeight = size.height
        val foregroundStart = 0f+canvasWidth/8
        val foregroundEnd = canvasWidth - canvasWidth/8
        val centreCircleStart = canvasWidth/2
        val circleRadius = 17/2
        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = 0f),
            color = progressBackGround,
            strokeWidth = canvasHeight
        )
        drawLine(
            start = Offset(x = foregroundStart, y = 0f),
            end = Offset(x = foregroundEnd, y = 0f),
            color = MainPurple,
            strokeWidth = canvasHeight
        )
        drawCircle(
            color = MainPurple,
            radius = circleRadius.dp.toPx(),
            center = Offset(x=foregroundStart, y = 0f)
        )
        drawCircle(
            color = MainPurple,
            radius = circleRadius.dp.toPx(),
            center = Offset(x=foregroundEnd, y = 0f)
        )
        drawCircle(
            color = MainPurple,
            radius = circleRadius.dp.toPx(),
            center = Offset(x=centreCircleStart, y = 0f)
        )

    }
}
@Composable
fun JobDetailsScreen(jobId:String){
    val job:Job
    val firstJob = Job(id = 1,
        location = "Gurugram, Haryana",
        jobTitle = "Swift Developer",
        companyDetails = "Company Details", jobType = "Full Time", description = "description",
        companyName = "Cisco",
        jobPostDate = "2 days ago",
        rating = "4.5",
        favorite = false,
        imageUrl = "https://res.cloudinary.com/ds8ursyfi/image/upload/v1690368712/jobs/swift.png")
    val secondJob = Job(id = 2,
        location = "Delhi, New Delhi",
        jobTitle = "C Sharp Developer",
        companyDetails = "Company Details", jobType = "Full Time", description = "description",
        companyName = "InfoSys",
        jobPostDate = "2 days ago",
        rating = "4.4",
        favorite = false,
        imageUrl = "https://res.cloudinary.com/ds8ursyfi/image/upload/v1690368732/jobs/Group_1286csharpicon.png")
    job = if(jobId == "1")
    {
        firstJob
    }
    else
    {
        secondJob
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp)
                .verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(job.imageUrl)
                .build(),
            modifier = Modifier
                .height(70.dp)
                .width(70.dp)
                .padding(bottom = 5.dp)
                .clip(RoundedCornerShape(2.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = "Job Icon Image",
        )
        Text(job.jobTitle, style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(500),
            color = BlackText,
        ) )
        Box(modifier = Modifier.padding(10.dp)
            .fillMaxWidth(0.9f)
            .height(17.dp)){
            CustomProgressBar()
        }
    }

}