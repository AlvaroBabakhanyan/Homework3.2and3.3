package tests;

import base.BaseTest;
import constants.dataProviders.dataProvider;
import constants.messages.AssertionMessages;
import org.testng.annotations.DataProvider;
import pages.HomesForSalePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageSearchTests extends BaseTest {

    @DataProvider
    public static Object[][] validSearchKeywords() {
        return dataProvider.VALID_SEARCH_KEYWORDS;
    }

    @DataProvider
    public static Object[][] invalidSearchKeywords() {
        return dataProvider.INVALID_SEARCH_KEYWORDS;
    }

    @Test(description = "Search for homes using valid keywords", dataProvider = "validSearchKeywords")
    public void testSuccessfulSearch(String keyword) {
        homePage.inputInSearchBar(keyword);
        HomesForSalePage homesForSalePage = homePage.clickSearchButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homesForSalePage.getHomeCardAddress(0).contains("NY"), AssertionMessages.SUCCESSFUL_SEARCH);
    }

    @Test(description = "Search for homes using invalid keywords", dataProvider = "invalidSearchKeywords")
    public void testUnsuccessfulSearch(String keyword) {
        homePage.inputInSearchBar(keyword);
        homePage.clickSearchButton();

        SoftAssert softAssert  = new SoftAssert();
        softAssert.assertEquals(homePage.getUnsuccessfulSearchMessage(), "Sorry, we couldn’t find '" + keyword + "'.");
    }
}
