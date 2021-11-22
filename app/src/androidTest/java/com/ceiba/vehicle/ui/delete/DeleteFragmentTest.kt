package com.ceiba.vehicle.ui.delete

import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ceiba.vehicle.MainActivity
import com.ceiba.vehicle.R
import com.schibsted.spain.barista.assertion.BaristaBackgroundAssertions.assertHasBackground
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem
import com.schibsted.spain.barista.interaction.BaristaSpinnerInteractions.clickSpinnerItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DeleteFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun infoInicialTest(){
        //clickOn("Add")
        Thread.sleep(1000)
        //Assert
        clickSpinnerItem(R.id.spTypeVehicle, 1)
    }

    @Test
    fun showDialogUrlTest(){
        //clickOn("Add")
        Thread.sleep(1000)
        //Action
        clickOn(R.id.ivIconoCamara)
        Thread.sleep(1000)
        //Assert
        assertDisplayed(R.id.btnAcceptDialogue, "Aceptar")
    }

    @Test
    fun emptyTest() {
        //Arrange
        clickOn("Delete")
        Thread.sleep(1000)
        writeTo(R.id.editTextSearchDelete   , "empty")
        ViewActions.closeSoftKeyboard()
        Thread.sleep(1000)

        //Assert
        assertListItemCount(R.id.recyclerViewSearchDelete, 0)
    }

    @Test
    fun listVehicleTest() {
        //Arrange
        clickOn("Delete")
        Thread.sleep(1000)

        //Assert
        assertListNotEmpty(R.id.recyclerViewSearchDelete)
    }
}