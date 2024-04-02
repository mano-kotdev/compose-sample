package com.manoj.composesample.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manoj.composesample.userDetails


@Composable
fun UserProfileDetailsScreen(userId: Int, navController: NavController?) {
    Scaffold(topBar = {
        AppBar(title = "User Profile Details", icon = Icons.Default.ArrowBack) {
            navController?.navigateUp()
        }
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                val userDetail = userDetails.first { userProfile -> userId == userProfile.id }
                ProfilePicture(userDetail.profilePicId, userDetail.status, 320.dp)
                ProfileContent(userDetail.name, userDetail.status, Alignment.CenterHorizontally)
            }
        }
    }
}
