package com.ceci.bookstore.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ceci.bookstore.R

@Composable
fun Login(modifier: Modifier = Modifier){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
    ){
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .width(54.dp)
                .height(54.dp)
        ){
         Icon(
             painter = painterResource(id = R.drawable.ic_arrow_back_ios),
             contentDescription = null )
        }
        
        Spacer(modifier = Modifier.height(50.dp))

    Text(
        text = stringResource(id = R.string.welcome_header_loginscreen),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.caption,
        color = Color.Black,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
        Spacer(modifier = Modifier.height(40.dp))
        TextField("Username")
        Spacer(modifier = Modifier.height(8.dp))
        TextField("Password")

        Row (
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ){
            TextButton(onClick = { }) {
                Text(text = "Forgot Password?", color = Color.Gray)
            }
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login_text),
                color = Color.White,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold
                )
        }

        Row(

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Divider(color = Color.Gray, modifier =  Modifier.width(120.dp))
            Text("Or Login with", modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            Divider(color = Color.Gray, modifier = Modifier.width(120.dp))
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top=24.dp)
            ) {
           SignUpButtom(painter = painterResource(id = R.drawable.ic_fb), {})
            Spacer(modifier = Modifier.width(16.dp))
           SignUpButtom(painter = painterResource(id = R.drawable.ic_google), {})
        }

    }
}

@Composable
fun SignUpButtom(painter: Painter, click :() -> Unit){
    OutlinedButton(
        onClick = click,
        modifier = Modifier.width(90.dp).height(45.dp)
        ) {
        Icon(
            painter = painter,
            contentDescription = null)
    }
}

@Composable
fun TextField(label : String)
{

    var text by rememberSaveable{
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange ={ text = it},
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
// @Preview
fun LoginPreview(){
    MaterialTheme{
        Scaffold {
            Login()
        }
    }
}