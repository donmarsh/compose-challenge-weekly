package org.marshsoft.jobsearch.views

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import org.marshsoft.jobsearch.R
import org.marshsoft.jobsearch.ui.theme.BlackText
import org.marshsoft.jobsearch.ui.theme.ButtonLightPurpleBackground
import org.marshsoft.jobsearch.ui.theme.MainPurple
import org.marshsoft.jobsearch.ui.theme.ResumeBoxBackground

private val pickImage = 100
private var imageUri: Uri? = null
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
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)

        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 35.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.image_resume),
                contentDescription = "Image of resumes",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier.padding(top = 35.dp, bottom = 10.dp),
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
            Box(
                modifier = Modifier
                    .padding(top = 35.dp, bottom = 35.dp)
                    .border(
                        width = 1.dp,
                        color = MainPurple,
                        shape = RoundedCornerShape(size = 30.dp)
                    )
                    .fillMaxWidth()
                    .height(162.dp)
                    .background(
                        color = ResumeBoxBackground,
                        shape = RoundedCornerShape(size = 30.dp)
                    ),

                )
            {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MainPurple,
                            containerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.simple_line_icons_cloud_upload),
                            contentDescription = "cloud upload icon"
                        )
                        Text(
                            text = "Upload a file", modifier = Modifier.fillMaxWidth(.5f),
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                IconButton(
                    onClick = { openFileManager(context = context) },
                    modifier = Modifier
                        .padding(end = 47.dp)
                        .clip(CircleShape)
                        .background(ButtonLightPurpleBackground)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bi_camera), tint = MainPurple,
                        contentDescription = "camera icon"
                    )
                }
                IconButton(
                    onClick = { Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show() },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(ButtonLightPurpleBackground)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clarity_image_gallery_line),
                        tint = MainPurple,
                        contentDescription = "picture icons"
                    )
                }
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White, containerColor = MainPurple
                )
            )
            {
                Text(
                    "Apply Now",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}
fun openFileManager(context: Context){
    val intent = Intent()
    intent.action = Intent.ACTION_GET_CONTENT
    intent.type = "file/*"
    startActivity(context, intent,null)
}

