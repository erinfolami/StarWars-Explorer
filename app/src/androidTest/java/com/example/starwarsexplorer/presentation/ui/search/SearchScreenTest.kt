package com.example.starwarsexplorer.presentation.ui.search


import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.starwarsexplorer.MainActivity
import com.example.starwarsexplorer.utils.TestTags
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun initialUIElements_displayed() {
        composeTestRule.activity.setContent {
            SearchScreen(onNavigateToResultsScreen = {})
        }

        composeTestRule.onNodeWithTag(TestTags.SearchInputField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.SearchButton).assertIsDisplayed()
    }

    @Test
    fun typingInSearchInput_updatesText() {
        composeTestRule.activity.setContent {
            SearchScreen(onNavigateToResultsScreen = {})
        }

        val inputNode = composeTestRule.onNodeWithTag(TestTags.SearchInputField)
        inputNode.performTextInput("Starship")
        inputNode.assertTextEquals("Starship")
    }

    @Test
    fun searchButton_isClickable() {
        composeTestRule.activity.setContent {
            SearchScreen(onNavigateToResultsScreen = {})
        }

        composeTestRule.onNodeWithTag("SearchButton")
            .assertIsEnabled()
            .performClick()
    }
}