package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

const val PUPPY = "PUPPY"

class PuppyDetailActivity : AppCompatActivity() {

    private val puppy by lazy { intent.extras!![PUPPY] as Puppy }

    companion object {
        @JvmStatic
        fun start(context: Context, puppy: Puppy) {
            val starter = Intent(context, PuppyDetailActivity::class.java).apply {
                putExtra(PUPPY, puppy)
            }
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                Detail()
            }
        }
    }

    @Composable
    private fun Detail() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = puppy.titleId)
                        )
                    },
                    elevation = 0.dp,
                    navigationIcon = {
                        IconButton(onClick = { finish() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "back",
                                Modifier.size(32.dp)
                            )
                        }
                    }
                )
            }
        ) {
            Column(modifier = Modifier.padding(8.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = puppy.painterId),
                    contentDescription = stringResource(id = puppy.detailId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = stringResource(id = puppy.detailId),
                    modifier = Modifier.padding(8.dp)
                )
                Button(onClick = { Toast.makeText(this@PuppyDetailActivity, R.string.adopt_click_hint, Toast.LENGTH_SHORT).show() }) {
                    Text(text = stringResource(R.string.adopt))
                }
            }
        }
    }
}