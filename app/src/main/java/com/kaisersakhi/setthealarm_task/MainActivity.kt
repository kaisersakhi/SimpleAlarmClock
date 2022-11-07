package com.kaisersakhi.setthealarm_task

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaisersakhi.setthealarm_task.ui.theme.SetTheAlarmTaskTheme
import com.kaisersakhi.setthealarm_task.utils.AppAlarm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetTheAlarmTaskTheme {
                MainScreen()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        AppAlarm.resetAlarmCounter()
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .fillMaxHeight(.50f),
            shape = RoundedCornerShape(CornerSize(15.dp)),
            elevation = 10.dp
        ) {

        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Multiple Alarm Setter",
                modifier = Modifier
                    .padding(all = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(100.dp))
            OutlinedButton(
                onClick = {
                    AppAlarm.setAlarm(context)
                   // Toast.makeText(context, "Alarm Has Been Set!", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(CornerSize(10.dp))
            ) {
                Text("Set Alarm", fontSize = 18.sp)
            }
        }
    }



}


