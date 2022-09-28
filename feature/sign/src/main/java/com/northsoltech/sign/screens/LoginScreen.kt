package com.northsoltech.sign.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.northsoltech.framework.R
import com.northsoltech.framework.components.CustomButton
import com.northsoltech.framework.states.UiState
import com.northsoltech.framework.ui.theming.Dimension

//@Preview
//@Composable
//fun PreviewLoginScreen() {
//    BikeTheme {
//        LoginScreen(viewModel = LoginViewModel(
//            signingRepository = SigningRepositoryImp(
//                signupDataSource = SigningDataSource
//            )
//        ),
//        onUserAuthentcated = {
//            Log.d(JE_TAG, "PreviewLoginScreen: onUserAuthentcated ")
//
//        },
//            onUserAuthentcateFailed = {
//                Log.d(JE_TAG, "PreviewLoginScreen: onUserAuthentcated ")
//
//            }
//        )
//    }
//}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    uiStates: MutableState<UiState>,
    onUserLogin:(phone:String, password:String)->Unit) {

    val uiState by remember { uiStates }
    var phoneNo by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    //Getting email
//    val focusManager = LocalFocusManager.current
    //Getting password
    val keyboardController = LocalSoftwareKeyboardController.current
    var isVisible by remember { mutableStateOf(false) }

    val icon = if (isVisible)
        painterResource(id = R.drawable.ic_baseline_visibility_24)
    else
        painterResource(id = R.drawable.ic_baseline_visibility_off_24)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimension.pagePadding)
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.appname),
            style = MaterialTheme.typography.h1.copy(fontSize = 48.sp),
            color = MaterialTheme.colors.primary,
            fontFamily = FontFamily.Cursive,
        )
        Spacer(modifier = Modifier.height(Dimension.pagePadding.times(3)))
        OutlinedTextField(
            value = phoneNo,
            textStyle = TextStyle(
                color = MaterialTheme.colors.secondary
            ),
            onValueChange = { phoneNo = it },
            label = { Text(text = "Phone Number") },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Person,
                    contentDescription = "phoneNo")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone
            ),
            keyboardActions = KeyboardActions(
                onNext = {
//                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(Dimension.pagePadding))

        OutlinedTextField(
            value = userPassword,
            textStyle = TextStyle(
                color = MaterialTheme.colors.secondary
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock,
                    contentDescription = "lock")
            },
            onValueChange = { userPassword = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
//                    viewModel.loginUser(email.text,
//                        getActivity.getString(R.string.jwt_token)
//                    )
                    keyboardController?.hide()
                }
            ),
            trailingIcon = {
                IconButton(onClick = {
                    isVisible = !isVisible
                }) {
                    Icon(painter = icon,
                        contentDescription = "view password")
                }
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(Dimension.pagePadding.times(2)))

        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimension.pagePadding.times(2),
                    end = Dimension.pagePadding.times(2))
                .shadow(
                    elevation = if (uiState !is UiState.Loading) Dimension.elevation else Dimension.zero,
                    shape = MaterialTheme.shapes.large,
                ),
            shape = MaterialTheme.shapes.large,
            padding = PaddingValues(Dimension.pagePadding.div(2)),
            buttonColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            text = stringResource(id = com.northsoltech.sign.R.string.login),
            enabled = uiState !is UiState.Loading,
            textStyle = MaterialTheme.typography.button,
            onButtonClicked = {
                onUserLogin.invoke(phoneNo,userPassword)
            },
            leadingIcon = {
                if (uiState is UiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(end = Dimension.pagePadding)
                            .size(Dimension.smIcon),
                        color = MaterialTheme.colors.onPrimary,
                        strokeWidth = Dimension.xs
                    )
                }
            }
        )
    }
}