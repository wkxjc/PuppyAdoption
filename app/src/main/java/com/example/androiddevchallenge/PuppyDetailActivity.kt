/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
