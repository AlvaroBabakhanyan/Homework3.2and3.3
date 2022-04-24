package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AffordabilityCalculatorPage;
import pages.HomesForSalePage;
import pages.PropertyDetailsPage;

public class GeneralTests extends BaseTest {
    @Test
    public void testFindingHomeWithAffordabilityCalculator() {
        //navigate to affordabilityCalculatorPage
        AffordabilityCalculatorPage affordabilityCalculatorPage = homePage.clickBuy_affordabilityCalculator();
        //input location
        affordabilityCalculatorPage.inputLocation("New York");
        //search for first suggestion
        affordabilityCalculatorPage.searchFirstSuggestionOfInputtedLocation();
        //get the recommended max price
        int maxPrice = affordabilityCalculatorPage.getMaxEstimatedPrice();
        //navigate to HomesForSale page
        HomesForSalePage homesForSalePage = affordabilityCalculatorPage.clickViewAllHomesOnMapButton();
        //go to nth homecard's PropertyDetails page
        PropertyDetailsPage propertyDetailsPage = homesForSalePage.clickHomeCard(0);
        //get the price from property page
        int propertyPrice = propertyDetailsPage.getPropertyValue();
        //make sure it is smaller than the max recomended price
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(maxPrice >= propertyPrice);
    }
}
