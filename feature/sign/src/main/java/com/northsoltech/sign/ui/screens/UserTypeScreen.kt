package com.northsoltech.sign.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.northsoltech.framework.components.CustomButton
import com.northsoltech.framework.components.MediumTitleText
import com.northsoltech.framework.ui.theming.BikeTheme
import com.northsoltech.framework.ui.theming.Dimension
import com.northsoltech.framework.utils.enums.UserType
import com.northsoltech.sign.R

@Preview
@Composable
fun PreviewUserScreen() {
    BikeTheme {
//        UserTypeScreen(onButtonClicked = {
//            Log.d(JE_TAG, "PreviewUserScreen: $it")
//        })
    }

}

@Composable
fun UserTypeScreen(
    onButtonClicked: (userType: UserType) ->  Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(Dimension.pagePadding.times(5)))
        Image(
            modifier = Modifier
                .height(Dimension.xlIcon)
                .width(Dimension.xlIcon),
            imageVector =ImageVector.vectorResource(id = R.drawable.ic_baseline_visibility_24) ,
            contentDescription = "app_icon")
        Spacer(modifier = Modifier.height(Dimension.pagePadding.times(2)))
        MediumTitleText(title = stringResource(id = R.string.are_you))
        Spacer(modifier = Modifier.height(Dimension.pagePadding.times(2)))
        CustomButton(
           modifier = Modifier
               .fillMaxWidth()
               .padding(start = Dimension.pagePadding.times(4),
                   end = Dimension.pagePadding.times(4)),
            padding = PaddingValues(Dimension.pagePadding.div(2)),
            textStyle = MaterialTheme.typography.button,
            buttonColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            text = stringResource(id = R.string.rider),
            onButtonClicked = {
                onButtonClicked(UserType.RIDER)
            }
        )
        Spacer(modifier = Modifier.height(Dimension.md))
        Text(text = stringResource(id = R.string.or))
        Spacer(modifier = Modifier.height(Dimension.md))
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimension.pagePadding.times(4),
                    end = Dimension.pagePadding.times(4)),
            buttonColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            padding = PaddingValues(Dimension.pagePadding.div(2)),
            text = stringResource(id = R.string.user),
            onButtonClicked = {
                onButtonClicked(UserType.USER)
            }
        )
    }
}