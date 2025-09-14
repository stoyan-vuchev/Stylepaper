package com.stoyanvuchev.stylepaper.core.etc

import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.stoyanvuchev.stylepaper.test.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UIStringTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testBasicComposeString() {
        composeRule.setContent {
            val basicString = remember { UIString.Basic("Hello, World!") }
            assertThat(basicString.asString()).isEqualTo("Hello, World!")
        }
    }

    @Test
    fun testResourceComposeString() {
        composeRule.setContent {
            val resourceString = remember { UIString.Resource(R.string.app_name) }
            assertThat(resourceString.asString())
                .isEqualTo(stringResource(R.string.app_name))
        }
    }

    @Test
    fun testBasicString() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val basicString = UIString.Basic("Hello, World!")
        assertThat(basicString.asString(context)).isEqualTo("Hello, World!")
    }

    @Test
    fun testResourceString() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val resourceString = UIString.Resource(R.string.app_name)
        assertThat(resourceString.asString(context))
            .isEqualTo(context.getString(R.string.app_name))
    }

}