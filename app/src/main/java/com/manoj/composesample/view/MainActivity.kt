package com.manoj.composesample.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.manoj.composesample.domain.dataModels.UserDetails
import com.manoj.composesample.ui.theme.ComposeSampleTheme
import com.manoj.composesample.userDetails


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                UserProfilesApplication()
            }
        }
    }
}

@Composable
fun UserProfilesApplication(userDetailsList: ArrayList<UserDetails> = userDetails) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "user_profiles") {
        composable(route = "user_profiles") {
            UserProfilesScreen(userDetailsList = userDetailsList, navController = navController)
        }
        composable(route = "user_profile_details/{userId}", arguments = listOf(
            navArgument("userId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )) {
            UserProfileDetailsScreen(it.arguments?.getInt("userId") ?: -1, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = { iconClickAction.invoke()}) {
            Icon(
                icon,
                contentDescription = "app bar icon",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    })
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL, showSystemUi = true
)
@Composable
fun UserProfilesScreenPreview() {
    ComposeSampleTheme {
        UserProfilesScreen(userDetailsList = userDetails, navController = null)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL, showSystemUi = true)
@Composable
fun UserProfileDetailsScreenPreview() {
    ComposeSampleTheme {
        UserProfileDetailsScreen(1, null)
    }
}
