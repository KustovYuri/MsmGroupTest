package com.example.msmgrouptest.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.msmgrouptest.ui.main.MainScreen
import com.example.msmgrouptest.ui.navigation.MsmNavigation
import com.example.msmgrouptest.ui.sing_in.SingInScreen
import com.example.msmgrouptest.ui.theme.MsmGroupTestTheme
import com.example.msmgrouptest.ui.theme.backgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("myMainActivity", "onCreate")
        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(color = backgroundColor)
                systemUiController.setNavigationBarColor(color = backgroundColor)
            }

            MsmNavigation()
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