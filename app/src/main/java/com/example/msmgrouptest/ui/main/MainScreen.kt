package com.example.msmgrouptest.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.msmgrouptest.R
import com.example.msmgrouptest.ui.theme.backgroundColor
import com.example.msmgrouptest.ui.theme.buttonColor
import com.example.msmgrouptest.ui.theme.cardColor
import com.example.msmgrouptest.ui.theme.textColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val viewModel = hiltViewModel<MainScreenViewModel>()

    Scaffold(
        topBar = {ExitButton()},
        containerColor = backgroundColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                DataCard(modifier = Modifier.padding())
            }
        }
    }
}


@Composable
fun DataCard(
    modifier: Modifier
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Данные пользователя:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column() {
                Text(
                    text = "Номер версии: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, buttonColor, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 18.dp),
                        text = "№1",
                        fontSize = 14.sp,
                    )
                }
            }
            Column() {
                Text(
                    text = "Имя пользователя: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, buttonColor, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 18.dp),
                        text = "TSD",
                        fontSize = 14.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column() {
                Text(
                    text = "Место подразделения: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, buttonColor, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 18.dp),
                        text = "Владивосток",
                        fontSize = 14.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column() {
                Text(
                    text = "Id пользователя: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, buttonColor, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 18.dp)
                            .padding(horizontal = 4.dp),
                        text = "7a613369-6b94-11e8-8b91-f07959941a7c",
                        fontSize = 14.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column() {
                Text(
                    text = "Id подразделения: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, buttonColor, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 18.dp)
                            .padding(horizontal = 4.dp),
                        text = "c3a21002-ef22-11e5-a605-f07959941a7c",
                        fontSize = 14.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            TimerUi()
        }
    }
}

@Composable
fun ExitButton(){
    val dialogIsOpen = remember { mutableStateOf(false) }

    if (dialogIsOpen.value)
        ExitDialog(openDialog = dialogIsOpen)

    Box(
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Личный кабинет",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = {dialogIsOpen.value = true}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ExitDialog(openDialog: MutableState<Boolean>){
    Dialog(onDismissRequest = { openDialog.value = false }) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = backgroundColor,
        ) {
            Column() {
                Text(
                    modifier = Modifier.padding(start = 18.dp, top = 18.dp),
                    text = "Выход",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Вы действительно хотите выйти из профиля?",
                        fontSize = 16.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, bottom = 10.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        TextButton(
                            onClick = { openDialog.value = false },
                        ) {
                            Text(
                                text = "ОТМЕНА",
                                fontSize = 14.sp,
                                color = textColor
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(onClick = { openDialog.value = false }) {
                            Text(
                                text = "ОК",
                                fontSize = 14.sp,
                                color = textColor
                            )
                        }
                    }
                }
            }
        }
    }
}