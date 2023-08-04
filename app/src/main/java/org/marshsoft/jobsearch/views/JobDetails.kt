package org.marshsoft.jobsearch.views

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.marshsoft.jobsearch.R
import org.marshsoft.jobsearch.entities.Job
import org.marshsoft.jobsearch.ui.theme.BlackText
import org.marshsoft.jobsearch.ui.theme.GreyText
import org.marshsoft.jobsearch.ui.theme.MainPurple
import org.marshsoft.jobsearch.ui.theme.ToggleGroupBackground
import org.marshsoft.jobsearch.ui.theme.ToggleGroupTextUnselected
import org.marshsoft.jobsearch.ui.theme.progressBackGround
import org.marshsoft.jobsearch.viewmodels.MainActivityViewModel

@Composable
fun JobDetails(jobId: String, navController: NavHostController )
{
    val job:Job
    val firstJob = Job(id = 1,
        location = "Gurugram, Haryana",
        jobTitle = "Swift Developer",
        companyDetails = "Company Details", jobType = "Full Time", jobDescription = "description",
        companyName = "Cisco",
        jobPostDate = "2 days ago",
        rating = "4.5",
        favorite = false,
        imageUrl = "https://res.cloudinary.com/ds8ursyfi/image/upload/v1690368712/jobs/swift.png")
    val secondJob = Job(id = 2,
        location = "Delhi, New Delhi",
        jobTitle = "C Sharp Developer",
        companyDetails = "Infosys Limited is an Indian multinational informa-tion technology company that provides business consulting, information technology and outsource services.",
        jobType = "Full Time", jobDescription = "\u2022 We are looking for a C# developer to build software using languages, technologies of the .NET framework. \n \u2022 You will create applications from scratch, configure existing systems and provide user support.Must have Potential to design, develop program independently. ",
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
    Scaffold(
        topBar = { TopBarJobDetails(job) },
        content = {innerPadding-> JobDetailsScreen(job = job, innerPadding) },

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
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back arrow")
        }},
        modifier = Modifier.background(color = Color.White))
}
@Composable
fun CustomProgressBar(){
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .height(6.dp)){
            val canvasWidth = size.width
            val canvasHeight = size.height
            val foregroundStart = 0f+canvasWidth/10
            val foregroundEnd = canvasWidth - canvasWidth/10
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
fun JobDetailsScreen(job:Job, innerPadding:PaddingValues, mainActivityViewModel: MainActivityViewModel = viewModel() ){
    val isAboutCompanyCurrent = mainActivityViewModel.isAboutCompanyCurrent.observeAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(start = 35.dp, end = 35.dp)
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
        Box( modifier = Modifier
            .fillMaxWidth()
            .height(17.dp) ){
            CustomProgressBar()
        }
        JobSalaryRatings(job = job)
        ToggleButtons()
        if(isAboutCompanyCurrent.value == true)
        {
            CompanyDescription(job  = job)
        }
        else{
            JobDescription(job = job)

        }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(
            contentColor = Color.White, containerColor = MainPurple))
        {
            Text("Apply Now", style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
            ) )
        }

    }

}
@Composable
fun JobSalaryRatings(job: Job) {
    Row(modifier = Modifier
        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Salary")
            Text("$10 k")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Type")
            Text("Full Time")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text("Ratings")
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.vectorstar),
                    contentDescription = "star",
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp)
                        .padding(end = 2.dp))
                Text(job.rating, modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                    style = TextStyle(fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = GreyText
                    ))
            }
        }
    }
    
}
@Composable
fun ToggleButtons(mainActivityViewModel: MainActivityViewModel = viewModel()){
    val isAboutCompanyCurrent = mainActivityViewModel.isAboutCompanyCurrent.observeAsState()
        val options = listOf(
            "About Company",
            "Job Description"
        )
        var selectedOption by remember {
            mutableStateOf("Job Description")
        }
    selectedOption = if(isAboutCompanyCurrent.value==true)
    {
        "About Company"
    }
    else
    {
        "Job Description"
    }
    val onSelectionChange = { text: String ->
                if(text=="About Company")
                {
                    mainActivityViewModel.setIsAboutCompany(true)
                }
        else{
                mainActivityViewModel.setIsAboutCompany(false)
                }
        }

        Row(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        size = 12.dp,
                    )
                )
                .background(color = Color.Transparent)
        ) {
            options.forEach { text ->
                Row(
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(
                        text = text, textAlign = TextAlign.Center,
                        color = if(text == selectedOption)Color.White else{
                            ToggleGroupTextUnselected},
                        modifier = Modifier
                            .fillMaxWidth()

                            .clickable {
                                onSelectionChange(text)
                            }
                            .background(
                                if (text == selectedOption) {
                                    MainPurple
                                } else {
                                    ToggleGroupBackground
                                }
                            )
                            .padding(
                                vertical = 12.dp,

                                ),
                    )
                }
            }
        }
    }

@Composable
fun JobDescription(job: Job){
    Column(modifier = Modifier
        .fillMaxWidth())
         {
        Text("About Job",style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(500),
            color = Color(0xFF1A1A1A),
        ) )
        Text(job.companyDetails, style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color(0x661A1A1A),
        ) )
        Text("Job Description",style = TextStyle(
                fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(500),
            color = Color(0xFF1A1A1A)
        ) )
        Text(job.jobDescription, style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color(0x661A1A1A),
        ) )
    }
}
@Composable
fun CompanyDescription(job: Job){
    Column(modifier = Modifier
        .fillMaxWidth())
    {
        Text("About Company",style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(500),
            color = Color(0xFF1A1A1A),
        ) )
    }
}