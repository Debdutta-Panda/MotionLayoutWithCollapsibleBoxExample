package com.debduttapanda.motionlayoutwithcollapsibleboxexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.debduttapanda.motionlayoutwithcollapsibleboxexample.ui.theme.MotionLayoutWithCollapsibleBoxExampleTheme
import kotlinx.coroutines.NonDisposableHandle.parent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutWithCollapsibleBoxExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainUI()
                }
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MainUI() {
    CollapsibleBox(
        modifier = Modifier
            .fillMaxSize()
        ,
        threshold = 0.05f
    ) {progress->
        MotionLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            start = ConstraintSet {
                val upper = createRefFor("upper")
                val lower = createRefFor("lower")
                constrain(upper){
                    width = Dimension.matchParent
                    height = Dimension.value(300.dp)
                }
                constrain(lower){
                    width = Dimension.matchParent
                    top.linkTo(upper.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
            },
            end = ConstraintSet {
                val upper = createRefFor("upper")
                val lower = createRefFor("lower")
                constrain(upper){
                    width = Dimension.matchParent
                    height = Dimension.value(200.dp)
                }
                constrain(lower){
                    width = Dimension.matchParent
                    top.linkTo(upper.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
            },
            progress = progress
        ) {
            Box(
                modifier = Modifier
                    .layoutId("upper")
                    .background(Color.Red)
            ){

            }
            Box(
                modifier = Modifier
                    .layoutId("lower")
                    .background(Color.Blue)
            ){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(100){
                        Text(it.toString())
                    }
                }
            }
        }
    }
}