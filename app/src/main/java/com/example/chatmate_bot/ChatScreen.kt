package com.example.chatmate_bot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatmate_bot.ui.theme.ColorModelMessage
import com.example.chatmate_bot.ui.theme.ColorModelMessageDark
import com.example.chatmate_bot.ui.theme.ColorUserMessage
import com.example.chatmate_bot.ui.theme.ColorUserMessageDark


@Composable
fun ChatPage(modifier: Modifier = Modifier,
             viewModel: ChatViewModel) {
    Column(
        modifier = modifier
    ) {
        AppHeader()
        MessageList(
            modifier = Modifier.weight(1f),
            messageList = viewModel.messageList
        )
        MessageInput(
            onMessageSend = {
                viewModel.sendMessage(it)
            }
        )
    }
}


@Composable
fun MessageList(modifier: Modifier = Modifier,messageList : List<MessageModel>) {
    if(messageList.isEmpty()){
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(id = R.drawable.iconimage),
                contentDescription = "Icon",

            )
            Text(modifier = Modifier.padding(12.dp), text = "Ask me anything", fontSize = 20.sp)
        }
    }else{
        LazyColumn(
            modifier = modifier,
            reverseLayout = true
        ) {
            items(messageList.reversed()){
                MessageRow(messageModel = it)
            }
        }
    }


}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role=="model"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .align(if (isModel) Alignment.BottomStart else Alignment.BottomEnd)
                    .padding(
                        start = if (isModel) 8.dp else 70.dp,
                        end = if (isModel) 70.dp else 8.dp,
                        top = 6.dp,
                        bottom = 6.dp
                    )
                    .clip(RoundedCornerShape(35f))
                    .background(
                        if (isSystemInDarkTheme()) {
                            if (isModel) ColorModelMessageDark else ColorUserMessageDark
                        }else { if (isModel) ColorModelMessage else ColorUserMessage }

                    )
                    .padding(6.dp)
            ) {

                SelectionContainer {
                    Text(
                        modifier = Modifier.padding(start = 5.dp , end = 5.dp),
                        text = messageModel.message,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color =    if (isSystemInDarkTheme()) {
                            Color.White
                        }else { if (isModel) Color.Black else Color.White }
                    )
                }


            }

        }


    }


}



@Composable
fun MessageInput(onMessageSend : (String)-> Unit) {

    var message by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier.padding(start = 12.dp, end = 8.dp, bottom = 12.dp , top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            shape = RoundedCornerShape(20f),
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = {
                message = it
            }
        )
        IconButton(onClick = {
            if(message.isNotEmpty()){
                onMessageSend(message)
                message = ""
            }

        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send"
            )
        }
    }
}


@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = if (isSystemInDarkTheme()) Color.Black else Color.White),

    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.padding(start = 15.dp, top = 8.dp , bottom = 8.dp),
                text = "ChatBot",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 18.sp
            )

            Icon(modifier = Modifier.padding(end = 8.dp),
                tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                imageVector = Icons.Default.MoreVert,
                contentDescription = "MoreMenu")

        }


    }
}