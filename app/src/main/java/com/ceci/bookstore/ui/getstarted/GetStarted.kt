package com.ceci.bookstore.ui.getstarted

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ceci.bookstore.R
import com.ceci.bookstore.ui.theme.BookStoreTheme

@Composable
fun GetStarted(modifier: Modifier = Modifier){
   Column(modifier = Modifier
       .fillMaxSize()
       .background(colorResource(id = R.color.blueOcean)),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ) {
       Image(
           painter = painterResource(id = R.drawable.illustration),
           contentDescription = null,
           modifier = Modifier
               .width(324.dp)
               .height(292.dp)
               .padding(top = 16.dp)
           )
      Text(
          text = stringResource(id = R.string.welcome_header_screen),
          textAlign = TextAlign.Start,
          style = MaterialTheme.typography.h1,
          color = Color.White,
          modifier = Modifier
              .width(324.dp)
              .padding(top = 16.dp)
      )
       Text(
           text = stringResource(id = R.string.welcome_description_screen),
           textAlign = TextAlign.Start,
           style = MaterialTheme.typography.body1,
           color = Color.White,
           modifier = Modifier
               .width(324.dp)
               .padding(top = 8.dp, bottom = 16.dp)
       )
       
       OutlinedButton(
           onClick = {  },
           modifier = Modifier
               .width(324.dp)
               .height(58.83.dp)
            ,
           colors = ButtonDefaults.buttonColors(
               backgroundColor = colorResource(id = R.color.getStarted_btnColor)
           )) {
           Text(
               text = stringResource(id = R.string.btn_description),
               color = Color.White,
               style = MaterialTheme.typography.body2
               )
       }

       Row(
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center
       ) {
           Text(
               text = stringResource(id = R.string.login_descrition),
               textAlign = TextAlign.Start,
               style = MaterialTheme.typography.body1,
               color = Color.White,
           )
           TextButton(
               onClick = { },
           ) {
               Text(
                   text = stringResource(id = R.string.login_text),
                   textAlign = TextAlign.Start,
                   style = MaterialTheme.typography.body2,
                   color = Color.White,
               )
           }
       }


       Text(
           text = stringResource(id = R.string.credits),
           textAlign = TextAlign.Center,
           style = MaterialTheme.typography.body1,
           color = Color.White,
           modifier = Modifier
               .width(324.dp)
               .padding(top = 64.dp)
       )
   }
}

@Composable
@Preview
fun GetStartedPreview(){
    BookStoreTheme {
        Scaffold {
            GetStarted()
        }
    }
}