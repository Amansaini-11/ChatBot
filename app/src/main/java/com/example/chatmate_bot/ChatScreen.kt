package com.example.chatmate_bot

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ChatScreen (modifier: Modifier = Modifier){

    Column(modifier = Modifier
        .padding(top = 36.dp),
        ) {
           AppHeader()
           MessageBox()
    }
}


@Composable
fun AppHeader (){

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)){

        Text(modifier = Modifier.padding(12.dp),
            text = "Chat Mate",
            color = Color.White,
            fontSize = 20.sp
            )

    }
}

@Composable
fun MessageBox(){

    var message by remember {
        mutableStateOf("")
    }
    Row (modifier = Modifier
        .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,){

        OutlinedTextField(value = message,
            onValueChange = {message = it}
        , Modifier.weight(1f))

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Send,
                contentDescription = "send")
            
        }

    }
}
