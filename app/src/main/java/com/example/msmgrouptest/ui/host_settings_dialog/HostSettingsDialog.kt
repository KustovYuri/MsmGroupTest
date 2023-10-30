package com.example.msmgrouptest.ui.host_settings_dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.msmgrouptest.ui.theme.backgroundColor
import com.example.msmgrouptest.ui.theme.buttonColor
import com.example.msmgrouptest.ui.theme.textColor

@Composable
fun HostSettingsDialog(openDialog: MutableState<Boolean>) {
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
                    HostSelector()
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
private fun HostSelector(){
    var name by remember { mutableStateOf(" ") }
    var expe by remember { mutableStateOf(false) }

    Column(){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { expe = it.isFocused },
            shape = RoundedCornerShape(13.dp),
            value = "fefufit.dvfu.ru",
            onValueChange = {},
            readOnly = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
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
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            for (i in 1..5){
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(text = "$i")}
                )
            }
        }
    }
}