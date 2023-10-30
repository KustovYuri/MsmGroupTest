package com.example.msmgrouptest.ui.host_settings_dialog

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.msmgrouptest.R
import com.example.msmgrouptest.ui.theme.backgroundColor
import com.example.msmgrouptest.ui.theme.buttonColor
import com.example.msmgrouptest.ui.theme.textColor

@Composable
fun HostSettingsDialog(openDialog: MutableState<Boolean>) {

    val viewModel = hiltViewModel<HostSettingsDialogViewModel>()

    Dialog(onDismissRequest = { openDialog.value = false }) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = backgroundColor,
        ) {
            Column() {
                Text(
                    modifier = Modifier.padding(start = 18.dp, top = 18.dp),
                    text = "Выберите адрес хоста:",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HostSelector(
                        selectedHost = viewModel.selectedHost.value,
                        hostsList = viewModel.hostName.value,
                        changeHost = {host:String-> viewModel.setSelectedHost(host)}
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, end = 10.dp, bottom = 10.dp),
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HostSelector(
    selectedHost:String,
    hostsList: List<String>,
    changeHost: (host:String) -> Unit
){
    var expe by remember { mutableStateOf(false) }


    Column(){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    expe = it.isFocused
                },
            shape = RoundedCornerShape(13.dp),
            value = selectedHost,
            onValueChange = {},
            readOnly = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .rotate(180f),
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
                    contentDescription = "arrow",
                    tint = buttonColor
                )
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = buttonColor,
                cursorColor = buttonColor
            )
        )
        DropdownMenu(
            expanded = expe,
            onDismissRequest = { expe = false },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .background(backgroundColor)
        ) {
            hostsList.forEach{
                DropdownMenuItem(
                    onClick = {
                        changeHost(it)
                        expe = false
                    },
                    text = { Text(text = it)},

                )
            }
        }
    }
}