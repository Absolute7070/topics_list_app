package com.example.listofcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listofcourses.Datasource.Datasource
import com.example.listofcourses.data.Topic
import com.example.listofcourses.ui.theme.ListOfCoursesTheme
import kotlin.math.ceil



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListOfCoursesTheme {
                topicsApp()
            }
        }
    }
}


// one topic item
@Composable
fun GridItem(topic: Topic, modifier: Modifier = Modifier) {
    val iconId = R.drawable.ic_grain  // dots icon's id

    Card(
        modifier = modifier
            .padding(8.dp)
            .border(
                BorderStroke(2.dp, Color(0XFF767272)),
                shape = RoundedCornerShape(4.dp)
            ),
        elevation = 4.dp
        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()  // fill max size of the card

        ) {
            // image column
            Column(
                modifier = Modifier.fillMaxWidth(0.3f)
            ) {
                Image(painter = painterResource(id = topic.imageResourceId),
                    contentDescription = stringResource(id =topic.stringResourceIdTopicName))

            }

            // text column
            Column(
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Row(modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = topic.stringResourceIdTopicName),
                        fontSize = 10.sp,
                        maxLines =1
                    )
                }

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.Start

                ) {
                    /*Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )*/
                    Text(
                        text = topic.numberOfCourse.toString()
                    )

                }
            }

        }
        
    }
}

// two columns of topics
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TopicList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(topicList) {topic -> GridItem(topic = topic)}
    }



}

@Composable
fun topicsApp() {
    ListOfCoursesTheme {
        Scaffold(
            content = { TopicList(topicList = Datasource().loadTopics()   ) }
        )
        
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    topicsApp()
}