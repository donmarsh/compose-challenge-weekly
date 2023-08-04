package org.marshsoft.jobsearch.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import org.marshsoft.jobsearch.R
import org.marshsoft.jobsearch.ui.theme.BlackText
import org.marshsoft.jobsearch.ui.theme.ButtonLightPurpleBackground
import org.marshsoft.jobsearch.ui.theme.MainPurple
import org.marshsoft.jobsearch.ui.theme.ResumeBoxBackground

@Composable
fun UploadResume() {
    Scaffold(
        topBar = { TopBarUploadResume() },
        content = {innerPadding-> UploadResumeScreen( innerPadding) },

        )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUploadResume() {
    val context = LocalContext.current
    CenterAlignedTopAppBar(title = { Text("Upload Resume") },
        navigationIcon = { IconButton(onClick = { Toast.makeText(context,"hello", Toast.LENGTH_SHORT).show() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back arrow")
        }
        },
        modifier = Modifier.background(color = Color.White))
}
@Composable
fun UploadResumeScreen(innerPadding:PaddingValues) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 35.dp, end = 35.dp)) {

        Image(painter = painterResource(id = R.drawable.image_resume),
            contentDescription ="Image of resumes",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillWidth)
        Text(modifier = Modifier.padding(top = 35.dp, bottom = 10.dp),
            text = "Upload your resume for more relevant jobs",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 36.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = BlackText,
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "You have not posted your resume yet, post now for more jobs",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(400),
                color = Color(0x661A1A1A),
                textAlign = TextAlign.Center,
            )
        )
        Box (
           modifier = Modifier.padding(top = 35.dp, bottom = 35.dp)
               .border(width = 1.dp, color = MainPurple, shape = RoundedCornerShape(size = 30.dp))
               .fillMaxWidth()
               .height(162.dp)
               .background(color = ResumeBoxBackground, shape = RoundedCornerShape(size = 30.dp)),

        )
        {
            Row(modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Button(onClick = {Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show()},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MainPurple,
                        containerColor = Color.Transparent
                    )) {
                        Icon(painter = painterResource(id = R.drawable.simple_line_icons_cloud_upload),
                            contentDescription = "cloud upload icon")
                        Text(
                            text = "Drop a file",modifier = Modifier.fillMaxWidth(.5f),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(500),
                                color = MainPurple,
                                textAlign = TextAlign.Center
                            )
                        )
                    }



            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            IconButton(onClick = { Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .padding(end = 47.dp)
                .clip(CircleShape)
                .background(ButtonLightPurpleBackground)
            ) {
                Icon(painter = painterResource(id = R.drawable.bi_camera), tint = MainPurple,
                    contentDescription = "camera icon")
            }
            IconButton(onClick = { Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(ButtonLightPurpleBackground)) {
                Icon(painter = painterResource(id = R.drawable.clarity_image_gallery_line),
                    tint = MainPurple,
                    contentDescription = "picture icons")
            }
        }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(top = 35.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, containerColor = MainPurple
            ))
        {
            Text("Apply Now",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center
            ) )
        }
    }
}