package com.example.msmgrouptest.ui.sing_in

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.msmgrouptest.R
import com.example.msmgrouptest.ui.host_settings_dialog.HostSettingsDialog
import com.example.msmgrouptest.ui.theme.backgroundColor
import com.example.msmgrouptest.ui.theme.buttonColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingInScreen(
    navigationToMainScreen: () -> Unit
) {

    val viewModel = hiltViewModel<SingInScreenViewModel>()

    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = context){
        viewModel.authEvents.collect{event ->
            when(event){
                is SingInScreenViewModel.SingInEvent.Success ->{
                    navigationToMainScreen()
                }
                is SingInScreenViewModel.SingInEvent.Error ->{
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsButton()
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    painter = painterResource(id = R.drawable.mcm_logo),
                    contentDescription = "logo",
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(50.dp))
                InputForm(viewModel)
                Spacer(modifier = Modifier.weight(1f))
                InputButton(
                    sendData = {viewModel.sendData()},
                    isLoading = viewModel.isLoadingData.value
                )
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputForm(viewModel: SingInScreenViewModel){

    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if(passwordVisibility){
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }else{
        painterResource(id = R.drawable.baseline_visibility_24)
    }

    Row(
        modifier = Modifier.fillMaxWidth(0.81f),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Логин",
            fontSize = 14.sp,
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.85f),
        shape = RoundedCornerShape(13.dp),
        value = viewModel.inputUserData.value.login,
        onValueChange = {
            viewModel.setLoginData(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = buttonColor,
            cursorColor = buttonColor
        )
    )


    Spacer(modifier = Modifier.height(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.81f),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Пароль",
            fontSize = 14.sp,
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.85f),
        shape = RoundedCornerShape(13.dp),
        value = viewModel.inputUserData.value.password,
        onValueChange = {
            viewModel.setPasswordData(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = buttonColor,
            cursorColor = buttonColor
        )
    )
    Spacer(modifier = Modifier.height(48.dp))
}

@Composable
private fun InputButton(
    sendData: () -> Unit,
    isLoading: Boolean
){
    Button(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(58.dp)
        ,
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        onClick = {
            sendData()
        }
    ) {
        if (!isLoading){
            Text(
                text = "Войти",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = backgroundColor
            )
        }else{
            CircularProgressIndicator(
                color = backgroundColor
            )
        }
    }
}

@Composable
fun SettingsButton(){
    val dialogIsOpen = remember { mutableStateOf(false) }

    if (dialogIsOpen.value)
        HostSettingsDialog(openDialog = dialogIsOpen)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            modifier = Modifier.padding(horizontal = 20.dp),
            onClick = {dialogIsOpen.value = true}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = null
            )
        }
    }
}
