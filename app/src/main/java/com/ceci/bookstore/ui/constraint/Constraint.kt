package com.ceci.bookstore.ui.constraint

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ceci.bookstore.R

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Constraint() {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (imgProfileImage, btnClickMe, txtResult, btnNext) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.illustration),
            contentDescription = null,
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .constrainAs(imgProfileImage) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )


        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .width(324.dp)
                .height(58.dp).constrainAs(btnClickMe){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.getStarted_btnColor)
            )
        ) {
            Text(
                text = stringResource(id = R.string.btn_description),
                color = Color.White,
                style = MaterialTheme.typography.body2
            )
        }


        Text(text = "Result", modifier = Modifier.constrainAs(txtResult){
            top.linkTo(btnClickMe.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end )
        })


        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .width(324.dp)
                .height(58.dp).constrainAs(btnNext){
                   end.linkTo(parent.end)
                   bottom.linkTo(parent.bottom)
                },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.teal_700)
            )
        ) {
            Text(
                text = stringResource(id = R.string.btn_description),
                color = Color.White,
                style = MaterialTheme.typography.body2
            )
        }

    }


}