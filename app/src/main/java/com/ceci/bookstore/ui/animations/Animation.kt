package com.ceci.bookstore.ui.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ceci.bookstore.R

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
fun MyAnimation() {

    var clicked by remember { mutableStateOf(false) }
    val transition = updateTransition(clicked, label = "")

    val buttonColor =
        transition.animateColor(label = "", transitionSpec = { tween(1000) }) { isClicked ->
            if (isClicked) Color.Green else Color.Red
        }

    val buttonShapeDp =
        transition.animateDp(label = "", transitionSpec = { tween(2000) }) { isClicked ->
            if (isClicked) 18.dp else 4.dp
        }

    val buttonAlignmentBias =
        transition.animateFloat(label = "", transitionSpec = { tween(2000) }) { isClicked ->
            if (isClicked) 1f else 0f
        }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = BiasAlignment(buttonAlignmentBias.value, buttonAlignmentBias.value)) {

        Button(
            onClick = { clicked = !clicked },
            colors = ButtonDefaults.buttonColors(buttonColor.value),
            shape = RoundedCornerShape(buttonShapeDp.value),
        ) {

            Text(text = if (!clicked) "Click me!" else "Clicked!")
        }
    }

}