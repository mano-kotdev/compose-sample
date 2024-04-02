package com.manoj.composesample.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.manoj.composesample.domain.dataModels.UserDetails
import com.manoj.composesample.ui.theme.LightGreen

@Composable
fun UserProfilesScreen(
    userDetailsList: ArrayList<UserDetails>,
    navController: NavController?
) {
    Scaffold(topBar = {
        AppBar("User Profiles", Icons.Default.Home) {

        }
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(userDetailsList) {
                    ProfileCard(userDetails = it) {
                        navController?.navigate("user_profile_details/${it.id}")
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard(userDetails: UserDetails, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = { clickAction.invoke() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userDetails.profilePicId, userDetails.status, 72.dp)
            ProfileContent(userDetails.name, userDetails.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(profilePicId: String, isActive: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = if (isActive) {
            BorderStroke(2.dp, MaterialTheme.colorScheme.LightGreen)
        } else {
            BorderStroke(2.dp, MaterialTheme.colorScheme.error)
        },
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        AsyncImage(
            model = profilePicId,
            contentDescription = "Profile Picture Icon",
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(username: String, isActive: Boolean, textAlignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = textAlignment
    ) {
        CompositionLocalProvider(
            if (isActive) LocalContentColor provides LocalContentColor.current.copy(
                alpha = 1f
            ) else LocalContentColor provides LocalContentColor.current.copy(alpha = 0.5f)
        ) {
            Text(text = username, style = MaterialTheme.typography.headlineMedium)
        }
        CompositionLocalProvider(
            if (isActive) LocalContentColor provides LocalContentColor.current.copy(
                alpha = 0.8f
            ) else LocalContentColor provides LocalContentColor.current.copy(alpha = 0.5f)
        ) {
            Text(
                text = if (isActive) {
                    "Active Now"
                } else {
                    "Inactive"
                }, style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}