package com.example.rmp_front

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.rmp_front.data.SessionManager
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SessionManagerTest {

    private lateinit var context: Context

    @Before
    fun setUp() = runBlocking {
        context = ApplicationProvider.getApplicationContext()
        SessionManager.clear(context)
    }

    @Test
    fun saveSession_then_getUserId_returns_saved_userId() = runBlocking {
        SessionManager.saveSession(context, userId = "user123", token = "token_abc")

        val userId = SessionManager.getUserId(context)

        assertEquals("user123", userId)
    }

    @Test
    fun clear_removes_userId() = runBlocking {
        SessionManager.saveSession(context, userId = "user123", token = null)

        SessionManager.clear(context)

        val userId = SessionManager.getUserId(context)
        assertNull(userId)
    }
}
