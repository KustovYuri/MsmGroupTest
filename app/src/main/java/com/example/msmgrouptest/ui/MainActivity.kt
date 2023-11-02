package com.example.msmgrouptest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.msmgrouptest.ui.main.MainScreen
import com.example.msmgrouptest.ui.navigation.MsmNavigation
import com.example.msmgrouptest.ui.sing_in.SingInScreen
import com.example.msmgrouptest.ui.theme.MsmGroupTestTheme
import com.example.msmgrouptest.ui.theme.backgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("myMainActivity", "onCreate")

        installSplashScreen().apply {
            setKeepVisibleCondition{
                viewModel.isLoading.value
            }
        }


        //SOFT_INPUT_KEYBOARD
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            val startScreen = viewModel.startScreen.value
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(color = backgroundColor, darkIcons = true)
                systemUiController.setNavigationBarColor(color = backgroundColor)
            }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = backgroundColor
            ) {
                if (startScreen != null){
                    MsmNavigation(startScreen)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("myMainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("myMainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("myMainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("myMainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("myMainActivity", "onDestroy")
    }
}