package com.sumit.customjetpackcomponents

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sumit.customjetpackcomponents.ui.theme.CustomJetpackComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomJetpackComponentsTheme {
                // A surface container using the 'background' color from the theme
                var isToggle by remember { mutableStateOf(false) }
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.White)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize().background(Color.White)
                    ) {
                        CustomSwitch(isSwitchToggled = isToggle) {
                            isToggle = it
                            if (isToggle) {
                                Log.d("ToggleAction", "ON")
                            } else {
                                Log.d("ToggleAction", "FF")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomSwitch(isSwitchToggled:Boolean = false,onToggle: (Boolean) -> Unit
){
    // Use animateColorAsState to animate the color change.
    val switchHandleColor by animateColorAsState(
        targetValue = if (isSwitchToggled) Color.Green else Color.Red,
        animationSpec = tween(durationMillis = 1000),
        label = "")

    // Use animateDpAsState to animate the padding change.
    val paddingStart by animateDpAsState(
        if (isSwitchToggled) 16.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )
    val paddingEnd by animateDpAsState(
        if (isSwitchToggled) 0.dp else 16.dp,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    Card(
        shape = RoundedCornerShape(100.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        border = BorderStroke(4.dp, Color.Black),
        modifier = Modifier
            .width(52.dp)
            .height(32.dp),
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .clickable {
                    onToggle(!isSwitchToggled)
                },
                contentAlignment = Alignment.Center){
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.switch_handle),
                        contentDescription = "",
                        tint = switchHandleColor,
                        modifier = Modifier.padding(start = paddingStart, end = paddingEnd)
                    )
                }
            }
        }
    )
}