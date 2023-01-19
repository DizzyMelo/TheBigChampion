package com.dicedev.thebigchampion.utils

import com.dicedev.thebigchampion.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

private val LOGIN_SCREEN_NAME = AppScreens.LoginScreen.name
private val HOME_SCREEN_NAME = AppScreens.HomeScreen.name

internal class UtilsTest {

    @Mock
    private lateinit var mockFirebaseUser: FirebaseUser

    @Test
    fun getNavigationRoute_NoUserLoggedIn() {
        val mockFirebaseAuth = mock<FirebaseAuth> {
            on { currentUser } doReturn null
        }

        val result = Utils.getNavigationRoute(mockFirebaseAuth)
        assert(result == LOGIN_SCREEN_NAME)
    }

    @Test
    fun getNavigationRoute_NoUserLoggedIn_Fail() {
        val mockFirebaseAuth = mock<FirebaseAuth> {
            on { currentUser } doReturn null
        }

        val result = Utils.getNavigationRoute(mockFirebaseAuth)
        assert(
            result != HOME_SCREEN_NAME,
            lazyMessage = { "Screen should be $LOGIN_SCREEN_NAME but found $HOME_SCREEN_NAME" })
    }

    @Test
    fun getNavigationRoute_UserLoggedIn() {
        mockFirebaseUser = mock()
        val mockFirebaseAuth = mock<FirebaseAuth> {
            on { currentUser } doReturn mockFirebaseUser
        }

        val result = Utils.getNavigationRoute(mockFirebaseAuth)
        assert(result == HOME_SCREEN_NAME)
    }


    @Test
    fun getNavigationRoute_UserLoggedIn_Fail() {
        mockFirebaseUser = mock()
        val mockFirebaseAuth = mock<FirebaseAuth> {
            on { currentUser } doReturn mockFirebaseUser
        }

        val result = Utils.getNavigationRoute(mockFirebaseAuth)
        assert(result != LOGIN_SCREEN_NAME)
    }
}